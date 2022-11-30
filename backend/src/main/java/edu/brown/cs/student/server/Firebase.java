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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.moshi.Moshi;

/*

public class Firebase {
    private FirebaseDatabase firebaseDatabase;
*/
/**
   * initialize firebase.
   *//*

   */
/**
   apiKey: "AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw",
     authDomain: "matched-cs320.firebaseapp.com",
     projectId: "matched-cs320",
     storageBucket: "matched-cs320.appspot.com",
     messagingSenderId: "352278494548",
     appId: "1:352278494548:web:4bc08b9d1dfa39d3909cc1",
     measurementId: "G-BTXZE45XGZ",
     databaseURL: "https://matched-cs320-default-rtdb.firebaseio.com/"
   *//*


  public void initFirebase() throws IOException {
      // .setDatabaseUrl("https://fir-66f50.firebaseio.com") - Firebase project url.
      // .setServiceAccount(new FileInputStream(new File("filepath"))) - Firebase private key file path.
      FileInputStream serviceAccount = new FileInputStream("./backend/matched-cs320-firebase-adminsdk-qt8u9-0a35976e64.json");
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount); //might need
      //
      FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
              .setDatabaseUrl("https://matched-cs320-default-rtdb.firebaseio.com/")
              //.setAPIKey("AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw")
              //.setServiceAccount(new FileInputStream(new File("C:\\Users\\Vicky\\Documents\\NetBeansProjects\\Examples\\src\\com\\javaquery\\google\\firebase\\Firebase-30f95674f4d5.json")))
              .setCredentials(credentials) //might need
              .build();

      FirebaseApp.initializeApp(firebaseOptions);

      firebaseDatabase = FirebaseDatabase.getInstance("https://matched-cs320-default-rtdb.firebaseio.com/");


  }

  static public HttpURLConnection tryRequest(String[] args) throws IOException, IOException {
    // Configure the connection (but don't actually send the request yet)
    URL requestURL = new URL("https://matched-cs320-default-rtdb.firebaseio.com/" + args[0] + "/" + args[1]);
    URL readableResponse = new URL("https://matched-cs320-default-rtdb.firebaseio.com/" + args[0] + "/" + args[1] + ".json?print=pretty");

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

  public String readDatabase(String[] args) throws URISyntaxException, IOException, InterruptedException {
      String startURL = "https://matched-cs320-default-rtdb.firebaseio.com/";
      StringBuilder URLTemplate = new StringBuilder();
      for (String eachString : args) {
          URLTemplate.append(eachString).append("/");
      }
      URI fullURL = new URI(startURL + URLTemplate + ".json");
      HttpRequest retrieveData = HttpRequest.newBuilder().uri(fullURL).GET().build();
      HttpResponse<String> dataResponse = HttpClient.newBuilder().build().send(retrieveData, HttpResponse.BodyHandlers.ofString());
      return dataResponse.body();
  }

  //replaces existing
  public void putDatabase(String[] args, String key, Object value) {
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
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String updateHelper(String endpoint, String key) throws URISyntaxException, IOException, InterruptedException {
        String startURL = "https://matched-cs320-default-rtdb.firebaseio.com/" + endpoint + "/" + key;
        URI fullURL = new URI(startURL + ".json");
        HttpRequest retrieveData = HttpRequest.newBuilder().uri(fullURL).GET().build();
        HttpResponse<String> dataResponse = HttpClient.newBuilder().build().send(retrieveData, HttpResponse.BodyHandlers.ofString());
        return dataResponse.body();
    }

    public void deleteFromDatabase(String[] args, String key) {

    }

    public void postDatabase() {

  }




}
*/
