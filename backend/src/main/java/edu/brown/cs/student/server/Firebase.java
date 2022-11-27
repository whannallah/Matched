package edu.brown.cs.student.server;

public class Firebase {
package edu.brown.cs.student.server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase() {

/**
   * initialize firebase.
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
   */
  private void initFirebase() {
      try {
          // .setDatabaseUrl("https://fir-66f50.firebaseio.com") - Firebase project url.
          // .setServiceAccount(new FileInputStream(new File("filepath"))) - Firebase private key file path.
          FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                  .setDatabaseUrl("https://matched-cs320-default-rtdb.firebaseio.com/")
                  .setAPIKey("AIzaSyCI_PJl30MJRkgTRKj6C6G97u83RmFQKgw")
                  //.setServiceAccount(new FileInputStream(new File("C:\\Users\\Vicky\\Documents\\NetBeansProjects\\Examples\\src\\com\\javaquery\\google\\firebase\\Firebase-30f95674f4d5.json")))
                  .build();

          FirebaseApp.initializeApp(firebaseOptions);
          firebaseDatabase = FirebaseDatabase.getInstance();

      } catch (FileNotFoundException ex) {
          ex.printStackTrace();
      }
}
}
