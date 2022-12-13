package edu.brown.cs.student.server;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CountDownLatch;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Splitter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firestore.v1.Document;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import org.apache.commons.codec.binary.StringUtils;


public class Firebase {
  private FirebaseDatabase firebaseDatabase;
  private User mostCompatible; //change this to a queue at some point
  private Boolean hasBeenInitiated=false;
  List<User> usersToReturn = new LinkedList<>();

/**
 * initialize firebase.
 *//*

   */

  /**
   * apiKey: "AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw",
   * authDomain: "matched-cs320.firebaseapp.com",
   * projectId: "matched-cs320",
   * storageBucket: "matched-cs320.appspot.com",
   * messagingSenderId: "352278494548",
   * appId: "1:352278494548:web:4bc08b9d1dfa39d3909cc1",
   * measurementId: "G-BTXZE45XGZ",
   * databaseURL: "https://matched-cs320-default-rtdb.firebaseio.com/"
   */



  public void initFirebase() throws IOException {
    FileInputStream serviceAccount =
        new FileInputStream("backend/matched-cs320-firebase-adminsdk-qt8u9-0a35976e64.json");
    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
    FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
        .setDatabaseUrl("https://matched-cs320-default-rtdb.firebaseio.com/")
        //.setAPIKey("AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw")
        //.setServiceAccount(new FileInputStream(new File("C:\\Users\\Vicky\\Documents\\NetBeansProjects\\Examples\\src\\com\\javaquery\\google\\firebase\\Firebase-30f95674f4d5.json")))
        .setCredentials(credentials)
        .build();
      FirebaseApp.initializeApp(firebaseOptions);


    firebaseDatabase =
        FirebaseDatabase.getInstance("https://matched-cs320-default-rtdb.firebaseio.com/");
    hasBeenInitiated=true;


  }

  public void unInitFirebase() {
    // Get the Firebase app instance
    FirebaseApp app = FirebaseApp.getInstance();
    // Delete the app instance and disconnect from the Firebase database
    app.delete();
  }

  static public HttpURLConnection tryRequest(String[] args) throws IOException, IOException {
    // Configure the connection (but don't actually send the request yet)
    URL requestURL =
        new URL("https://matched-cs320-default-rtdb.firebaseio.com/" + args[0] + "/" + args[1]);
    URL readableResponse = new URL(
        "https://matched-cs320-default-rtdb.firebaseio.com/" + args[0] + "/" + args[1] +
            ".json?print=pretty");

    System.out.println(requestURL);
    System.out.println(readableResponse);
    HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();

    clientConnection.connect();
    return clientConnection;
  }

  public Object createNewUser(User user) {
    Moshi moshi = new Moshi.Builder().build();
    return moshi.adapter(User.class).toJson(user);
  }

  //Returns THE JSON; does not use moshi so as to make the read fxn more generally useful
  public String readDatabase(String[] args)
      throws URISyntaxException, IOException, InterruptedException {
    String startURL = "https://matched-cs320-default-rtdb.firebaseio.com/";
    StringBuilder URLTemplate = new StringBuilder();
    for (String eachString : args) {
      URLTemplate.append(eachString).append("/");
    }
    URI fullURL = new URI(startURL + URLTemplate + ".json");
    HttpRequest retrieveData = HttpRequest.newBuilder().uri(fullURL).GET().build();
    HttpResponse<String> dataResponse =
        HttpClient.newBuilder().build().send(retrieveData, HttpResponse.BodyHandlers.ofString());
    return dataResponse.body();
  }

    public void putDatabase2(ArrayList<String> args, String key, Object value) {
        try {
            StringBuilder endpoint = new StringBuilder();
            for (String eachString : args) {
                endpoint.append(eachString).append("/");
            }
            DatabaseReference ref = firebaseDatabase.getReference(endpoint +"/" + key);
            final CountDownLatch latch = new CountDownLatch(1);
            System.out.println("key: " + ref.getKey());
            System.out.println("root: " + ref.getRoot());
            System.out.println("parent: " + ref.getParent());
            ref.setValue(value, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                    latch.countDown();
                } else {
                    System.out.println("Data saved successfully.");
                    latch.countDown();
                }
            });
            latch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

  //replaces existing
  public void putDatabase(String[] args, String key, Object value) {
    try {
      StringBuilder endpoint = new StringBuilder();
      for (String eachString : args) {
        endpoint.append(eachString).append("/");
      }
      DatabaseReference ref = firebaseDatabase.getReference(endpoint + "/" + key);
      final CountDownLatch latch = new CountDownLatch(1);
      System.out.println("key: " + ref.getKey());
      System.out.println("root: " + ref.getRoot());
      System.out.println("parent: " + ref.getParent());
      ref.setValue(value, (databaseError, databaseReference) -> {
        if (databaseError != null) {
          System.out.println("Data could not be saved " + databaseError.getMessage());
          latch.countDown();
        } else {
          System.out.println("Data saved successfully.");
          latch.countDown();
        }
      });
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void updateDatabase(String[] args, String key, Object value) {

    try {
      StringBuilder endpoint = new StringBuilder();
      for (String eachString : args) {
        endpoint.append(eachString).append("/");
      }
      DatabaseReference ref = firebaseDatabase.getReference(String.valueOf(endpoint));
      final CountDownLatch latch = new CountDownLatch(1);
      System.out.println("key: " + ref.getKey());
      System.out.println("root: " + ref.getRoot());
      System.out.println("parent: " + ref.getParent());
      HashMap<String, Object> map = new HashMap();
      map.put(key, value);
      System.out.println(this.readDatabase(args) + "here");
      if (!this.updateHelper(String.valueOf(endpoint), key).equals("null")) {
        ref.updateChildren(map, ((databaseError, databaseReference) -> {
          if (databaseError != null) {
            System.out.println("Data could not be saved " + databaseError.getMessage());
            latch.countDown();
          } else {
            System.out.println("Data saved successfully.");
            latch.countDown();
          }
        }));
        latch.await();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String updateHelper(String endpoint, String key)
      throws URISyntaxException, IOException, InterruptedException {
    String startURL = "https://matched-cs320-default-rtdb.firebaseio.com/" + endpoint + "/" + key;
    URI fullURL = new URI(startURL + ".json");
    HttpRequest retrieveData = HttpRequest.newBuilder().uri(fullURL).GET().build();
    HttpResponse<String> dataResponse =
        HttpClient.newBuilder().build().send(retrieveData, HttpResponse.BodyHandlers.ofString());
    return dataResponse.body();
  }

  public void deleteFromDatabase(String[] args, String key) {
      try {
        StringBuilder endpoint = new StringBuilder();
        for (String eachString : args) {
          endpoint.append(eachString).append("/");
        }
        DatabaseReference userRef = firebaseDatabase.getReference(endpoint + "/" + key);
        final CountDownLatch latch = new CountDownLatch(1);
        System.out.println("key: " + userRef.getKey());
        System.out.println("root: " + userRef.getRoot());
        System.out.println("parent: " + userRef.getParent());
        userRef.removeValue( (databaseError, databaseReference) -> {
          if (databaseError != null) {
            System.out.println("User could not be deleted:  " + databaseError.getMessage());
            latch.countDown();
          } else {
            System.out.println("User deleted successfully.");
            latch.countDown();
          }
        });
        latch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }






  //dont forget to not include mainUser !!
  public void loop(String root, User mainUser)
      throws URISyntaxException, IOException, InterruptedException {
    //System.out.println("got into loop");
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Get a reference to the "users" location in the database
    DatabaseReference usersRef = database.getReference(root);

    final CountDownLatch latch = new CountDownLatch(1);
    // Query the database for all child nodes under the root node
    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

      @Override
      public void onDataChange(DataSnapshot snapshot) {
        //System.out.println("got to inner loop");
        double maxCosSim = -2.0;

        Comparator<Map.Entry<User, Double>> comparator = new Comparator<Map.Entry<User, Double>>() {
          @Override
          public int compare(Map.Entry<User, Double> e1, Map.Entry<User, Double> e2) {
            return e2.getValue().compareTo(e1.getValue());
          }
        };

        // Create a priority queue using the Comparator
        PriorityQueue<Map.Entry<User, Double>> pq = new PriorityQueue<>(comparator);
        HashMap<User, Double> map = new HashMap<>();
        // Loop through all child nodes
        for (DataSnapshot userSnapshot : snapshot.getChildren()) {

          // Get the user object from the snapshot
          //System.out.println(userSnapshot.getValue().toString());
          Moshi moshi2 = new Moshi.Builder().build();
          try {
            User user =
                moshi2.adapter(User.class).fromJson(userSnapshot.getValue().toString());
            List<List<Float>> CompEmbedding = user.getEmbedding();
            double cosineSim = cosineSimAverage(mainUser.getEmbedding(), CompEmbedding);
            map.put(user, cosineSim);
            System.out.println("map size" + map.size());


            // Do something with the field
            //System.out.println(CompEmbedding.get(0).get(0));

          } catch (IOException e) {

            e.printStackTrace();
          }

          // Access the specific field you are interested in
          //List<List<Float>> embedding = user.getEmbedding();

          // Do something with the field
          //System.out.println(embedding.get(0).get(0));

        }
        System.out.println("got to end of loop");
        pq.addAll(map.entrySet());
        Map.Entry<User, Double> entry = pq.poll();
        System.out.println(entry.getValue());
        usersToReturn.add(entry.getKey());


        Map.Entry<User, Double> entry2 = pq.poll();
        System.out.println(entry2.getValue());
        usersToReturn.add(entry2.getKey());

        while (!pq.isEmpty()) {
          System.out.println(pq.poll());
        }

        latch.countDown();
      }

      @Override
      public void onCancelled(DatabaseError error) {
        System.out.println("got to error");
        return;
      }
    }
    );
    latch.await();
    System.out.println("got out of nest");
  }

  public List<User> otherLoop(String root, String mainUserKey)
          throws URISyntaxException, IOException, InterruptedException {
    //System.out.println("got into loop");
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Get a reference to the "users" location in the database
    DatabaseReference usersRef = database.getReference(root);

    final CountDownLatch latch = new CountDownLatch(1);
    // Query the database for all child nodes under the root node
    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

      @Override
      public void onDataChange(DataSnapshot snapshot) {
        //System.out.println("got to inner loop");
        double maxCosSim = -2.0;
        Comparator<Map.Entry<User, Double>> comparator = new Comparator<Map.Entry<User, Double>>() {
          @Override
          public int compare(Map.Entry<User, Double> e1, Map.Entry<User, Double> e2) {
            return e2.getValue().compareTo(e1.getValue());
          }
        };
        // Create a priority queue using the Comparator
        PriorityQueue<Map.Entry<User, Double>> pq = new PriorityQueue<>(comparator);
        HashMap<User, Double> map = new HashMap<>();
        Moshi moshi2 = new Moshi.Builder().build();
        User mainUser = null;


        //find mainUser
        for (DataSnapshot userSnapshot: snapshot.getChildren()) {
          if (Objects.equals(userSnapshot.getKey(), mainUserKey)) {
            try {
              mainUser = moshi2.adapter(User.class).fromJson(userSnapshot.getValue().toString());
              break;
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        //http://localhost:9000/getMatches?user-key=samantha_shulman&Qtype=users-date
        // Loop through all child nodes
        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
          // Get the user object from the snapshot
          try {
            User user = null;
            if (Objects.equals(userSnapshot.getKey(), mainUserKey)) {
              continue;
            }
            else {
              user = moshi2.adapter(User.class).fromJson(userSnapshot.getValue().toString());
            }
            if (mainUser != null && user != null) {
              List<List<Float>> CompEmbedding = user.getEmbedding();
              double cosineSim = cosineSimAverage(mainUser.getEmbedding(), CompEmbedding);
              map.put(user, cosineSim);
              System.out.println("map size" + map.size());
            }
            // Do something with the field
            //System.out.println(CompEmbedding.get(0).get(0));
          } catch (IOException e) {
            e.printStackTrace();
          }
          // Access the specific field you are interested in
          //List<List<Float>> embedding = user.getEmbedding();
          // Do something with the field
          //System.out.println(embedding.get(0).get(0));
        }
        System.out.println("got to end of loop");
        if (map.isEmpty()) {
          System.out.println("map is empty");
        }
        pq.addAll(map.entrySet());
        System.out.println(map.size());

        for (int i=0; i<3; i++){
          if(!pq.isEmpty()){
            Map.Entry<User, Double> entry = pq.poll();
            usersToReturn.add(entry.getKey());
          }
        }

        System.out.println("got to latch countdown");
        latch.countDown();
      }
      @Override
      public void onCancelled(DatabaseError error) {
        System.out.println("got to error");
        return;
      }
    });
    latch.await();
    System.out.println("got out of nest");

    return usersToReturn;
  }

  public double cosineSimAverage(List<List<Float>> mainUser, List<List<Float>> compUser) {
    Double score = 0.0;
    for (int i = 0; i < mainUser.size(); i++) {
      score += cosineSimilarity(mainUser.get(i), compUser.get(i));
    }
    return score / mainUser.size();
  }

    public static double cosineSimilarity(List<Float> vec1, List<Float> vec2) {
      int commonElements = 0;
      double dotProduct = 0;
      double sum1 = 0;
      double sum2 = 0;

      for (int i = 0; i < vec1.size() && i < vec2.size(); i++) {
        dotProduct += vec1.get(i) * vec2.get(i);
        sum1 += vec1.get(i) * vec1.get(i);
        sum2 += vec2.get(i) * vec2.get(i);
        commonElements++;
      }

      for (int i = commonElements; i < vec1.size(); i++) {
        sum1 += vec1.get(i) * vec1.get(i);
      }

      for (int i = commonElements; i < vec2.size(); i++) {
        sum2 += vec2.get(i) * vec2.get(i);
      }

      double magnitude = Math.sqrt(sum1) * Math.sqrt(sum2);

      if (magnitude == 0) {
        return 0;
      }

      return dotProduct / magnitude;
    }

  public List<User> getUsersToReturn(){
    return this.usersToReturn;

  }
}


//  public void testLoop(String root, User mainUser) {
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//    // Get a reference to the "users" location in the database
//    DatabaseReference usersRef = database.getReference("PerfectDate/Test-User");
//
//    // Query the database for all child nodes under the root node
//    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
//
//      @Override
//      public void onDataChange(DataSnapshot snapshot) {
//        double maxCosSim = -2.0;
//        // Loop through all child nodes
//        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//          String[] newStrings = userSnapshot.getValue().toString().split("dataNext=" + "\\{" + "data=");
//          StringBuilder stringBuilder = new StringBuilder();
//          for (String each: newStrings) {
//            String newString = each.substring(0, each.length() - 2);
//            stringBuilder.append(newString);
//          }
//          String newString = stringBuilder.toString().replace("=", ":").split("}")[0];
//          String newerString = newString.replace("{data:", "");
//          newerString += ",\"classYear\":\"2024\",\"email\":\"whi@gmail.edu\",\"name\":\"w\",\"pronouns\":\"she\",\"questionnaireType\":\"date\"";
//          newerString += "}";
//          newerString = "{\"embedding\":" + newerString;
//          Moshi moshi2 = new Moshi.Builder().build();
//          try {
//            /**
//             * Here is where the changed embedding format needs to be accounted for
//             */
//            System.out.println(newerString);
//            User user = moshi2.adapter(User.class)
//                    .fromJson(newerString);
//            assert user != null;
//            List<List<Float>> CompEmbedding = user.getEmbedding();
//            double cosineSim = cosineSimAverage(mainUser.getEmbedding(), CompEmbedding);
//            if (cosineSim > maxCosSim) {
//              maxCosSim = cosineSim;
//              mostCompatible = user;
//            }
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//          // Access the specific field you are interested in
//          //List<List<Float>> embedding = user.getEmbedding();
//          // Do something with the field
//          //System.out.println(embedding.get(0).get(0));
//        }
//        System.out.println("got to end of loop");
//      }
//      @Override
//      public void onCancelled(DatabaseError error) {
//        System.out.println("got to error");
//        return;
//      }
//    });System.out.println("got out of nest");
//  }

//  public void putEmbeddingInDatabase(String[] args, String key, String value) throws IOException {
//    int chunkSize = 400;
//    if (value.length() > chunkSize) {
//      System.out.println("bigger than chunk size");
//      Iterable<String> chunks = Splitter.fixedLength(chunkSize).split(value);
//      ArrayList<String> oldArgs = new ArrayList<>(Arrays.asList(args));
//      oldArgs.add("embedding");
//      for (String chunk : chunks) {
//        System.out.println(chunk);
//        putDatabase2(oldArgs, "data", chunk);
//        oldArgs.add("dataNext");
//      }
//    }
//    else {
//      ArrayList<String> newArgs = new ArrayList<>(Arrays.asList(args));
//      putDatabase2(newArgs, "data", value);
//    }
//  }
