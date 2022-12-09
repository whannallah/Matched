package edu.brown.cs.student.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CountDownLatch;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.moshi.Moshi;


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
    // .setDatabaseUrl("https://fir-66f50.firebaseio.com") - Firebase project url.
    // .setServiceAccount(new FileInputStream(new File("filepath"))) - Firebase private key file path.
    FileInputStream serviceAccount =
        new FileInputStream("matched-cs320-firebase-adminsdk-qt8u9-0a35976e64.json");
    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount); //might need
    //
    FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
        .setDatabaseUrl("https://matched-cs320-default-rtdb.firebaseio.com/")
        //.setAPIKey("AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw")
        //.setServiceAccount(new FileInputStream(new File("C:\\Users\\Vicky\\Documents\\NetBeansProjects\\Examples\\src\\com\\javaquery\\google\\firebase\\Firebase-30f95674f4d5.json")))
        .setCredentials(credentials) //might need
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

  }

  public void postDatabase() {

  }

  //dont forget to not include mainUser !!
  public void loop(String root, User mainUser)
      throws URISyntaxException, IOException, InterruptedException {
    System.out.println("got into loop");
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Get a reference to the "users" location in the database
    DatabaseReference usersRef = database.getReference(root);

    final CountDownLatch latch = new CountDownLatch(1);
    // Query the database for all child nodes under the root node
    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("got to inner loop");
        double maxCosSim = -2.0;

        Comparator<Map.Entry<User, Double>> comparator = new Comparator<Map.Entry<User, Double>>() {
          @Override
          public int compare(Map.Entry<User, Double> e1, Map.Entry<User, Double> e2) {
            return e1.getValue().compareTo(e2.getValue());
          }
        };

        // Create a priority queue using the Comparator
        PriorityQueue<Map.Entry<User, Double>> pq = new PriorityQueue<>(comparator);
        HashMap<User, Double> map = new HashMap<>();
        // Loop through all child nodes
        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
          System.out.println("got here");
          // Get the user object from the snapshot
          System.out.println(userSnapshot.getValue().toString());
          Moshi moshi2 = new Moshi.Builder().build();
          try {
            User user =
                moshi2.adapter(User.class).fromJson(userSnapshot.getValue().toString());
            List<List<Float>> CompEmbedding = user.getEmbedding();
            double cosineSim = cosineSimAverage(mainUser.getEmbedding(), CompEmbedding);
            map.put(user, cosineSim);
            System.out.println("map size" + map.size());

            // Do something with the field
            System.out.println(CompEmbedding.get(0).get(0));

          } catch (IOException e) {
            e.printStackTrace();
          }

          // Access the specific field you are interested in
          //List<List<Float>> embedding = user.getEmbedding();

          // Do something with the field
          //System.out.println(embedding.get(0).get(0));

        }
        System.out.println("got to end of loop");
        System.out.println(usersToReturn.size());
        pq.addAll(map.entrySet());
        System.out.println(usersToReturn.size());
        Map.Entry<User, Double> entry = pq.poll();
        System.out.println(entry);
        usersToReturn.add(entry.getKey());
        System.out.println(usersToReturn.size());

        Map.Entry<User, Double> entry2 = pq.poll();
        System.out.println(entry2);
        usersToReturn.add(entry2.getKey());

        System.out.println(usersToReturn.size());

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

  public double cosineSimAverage(List<List<Float>> mainUser, List<List<Float>> compUser) {
    Double score = 0.0;
    for (int i = 0; i < mainUser.size(); i++) {
      score += cosineSimilarity(mainUser.get(i), compUser.get(i));
    }
    return score / mainUser.size();
  }

  public double cosineSimilarity(List<Float> A, List<Float> B) {
    // Calculate the dot product of the vectors
    double dotProduct = 0;
    for (int i = 0; i < A.size(); i++) {
      dotProduct += A.get(i) * B.get(i);
    }

    // Calculate the length of vector A
    double A_length = 0;
    for (int i = 0; i < A.size(); i++) {
      A_length += A.get(i) * A.get(i);
    }
    A_length = Math.sqrt(A_length);

    // Calculate the length of vector B
    double B_length = 0;
    for (int i = 0; i < B.size(); i++) {
      B_length += B.get(i) * B.get(i);
    }
    B_length = Math.sqrt(B_length);

    // Calculate the cosine similarity
    double cosineSimilarity = dotProduct / (A_length * B_length);
    return cosineSimilarity;
  }

  public List<User> getUsersToReturn(){
    return this.usersToReturn;
  }
}

