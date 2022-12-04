package edu.brown.cs.student.server;

import static spark.Spark.after;

import java.io.IOException;
import java.net.URISyntaxException;

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
    FormData object = new FormData();
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

    QuestionnaireAPIHandler Qhandler = new QuestionnaireAPIHandler(object);
    Spark.get("getQuestionairreResponse", Qhandler);

    Spark.get("getCohereResponse", new CohereAPIHandler(object));

//    //Firebase stuff
//    Firebase firebase = new Firebase();
//    firebase.initFirebase();
//    String[] test = {"users"};
//    String[] badTest = {"noname"};
//    String[] putTest = {"users"};
//    String[] putTest2 = {"users", "575746"};
//    System.out.println(firebase.readDatabase(test) + "here");
//    ;

    //Spark.get("getCohereResponse", new CohereAPIHandler(params.get(0), params.get(1)));

   /* //Firebase stuff
    Firebase firebase = new Firebase();
    firebase.initFirebase();

    //Example creating a user:
    //1) Mock Data:
    String[] userRoot = {"users"};
    List<Float> singleList1 = new ArrayList();
    singleList1.add(Float.parseFloat("1.0"));
    singleList1.add(Float.parseFloat("2.0"));
    List<Float> singleList2 = new ArrayList();
    singleList2.add(Float.parseFloat("3.0"));
    singleList2.add(Float.parseFloat("4.0"));
    List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
    exampleEmbedding.add(singleList1);
    exampleEmbedding.add(singleList2);
    //2) Actually adding the user:
    User mainUser = new User("Whitney", "she/her", "2024", "I<3HarryStyles@brown.edu",exampleEmbedding);
    User sideUser = new User("Emily", "go/crazy", "2024", "yomama@brown.edu", exampleEmbedding);
    firebase.putDatabase(userRoot, mainUser.getEmailWithoutEdu(), firebase.createNewUser(mainUser));
    firebase.putDatabase(userRoot, sideUser.getEmailWithoutEdu(), firebase.createNewUser(sideUser));

    String[] putTest = {"users"};
    //UPDATE:
    firebase.updateDatabase(putTest, "BBSSS", "hello"); //doesn't do anything because BBSSS doesn't exist in database

    //POST: doesn't overwrite, will create a child (Not sure we really need)


    //UPDATE:
//    firebase.updateDatabase(putTest, "BBSSS", "hello");


    //DELETE:


*/
    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.");
  }
}
