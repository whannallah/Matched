package edu.brown.cs.student.server;

import static spark.Spark.after;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import spark.Spark;

/**
 * Top-level class to run our API server. Contains the main() method which starts Spark and runs the
 * various handlers.
 *
 * <p>We have three endpoints: loadcsv, getcsv, and weather. loadcsv and getcsv utilize a shared
 * state, csvDatabase.
 */
public class Server {

  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
    Spark.port(9000);
    /*
       Setting CORS headers to allow cross-origin requests from the client; this is necessary for the client to
       be able to make requests to the server.

       By setting the Access-Control-Allow-Origin header to "*", we allow requests from any origin.
       This is not a good idea in real-world applications, since it opens up your server to cross-origin requests
       from any website. Instead, you should set this header to the origin of your client, or a list of origins
       that you trust.

       By setting the Access-Control-Allow-Methods header to "*", we allow requests with any HTTP method.
       Again, it's generally better to be more specific here and only allow the methods you need, but for
       this demo we'll allow all methods.

       We recommend you learn more about CORS with these resources:
           - https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
           - https://portswigger.net/web-security/cors
    */
    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    // Setting up the handler for the GET endpoints
    Spark.get("getCohereResponse", new CohereAPIHandler("whitney", "goodbye"));

    //Firebase stuff
    Firebase firebase = new Firebase();
    firebase.initFirebase();
    String[] test = {"users"};
    String[] badTest = {"noname"};
    String[] putTest = {"users"};
    String[] putTest2 = {"users", "575746"};
    System.out.println(firebase.readDatabase(test) + "here");
    ;

    //PUT: overwrites if the key already exists
//    firebase.putDatabase(putTest, "38346", "dumb");
//    firebase.putDatabase(putTest, "575746", "smart");
//    firebase.putDatabase(putTest2, "Age", "23");

    //POST: doesn't overwrite, will create a child (Not sure we really need)

    //UPDATE:
    firebase.updateDatabase(putTest, "BBSSS", "hello");

    //DELETE:

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.");
  }
}
