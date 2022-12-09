package edu.brown.cs.student.server;

import static spark.Spark.after;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
    FormData object = new FormData();
    Spark.port(9000);
    Firebase firebase = new Firebase();
    firebase.initFirebase();
    String[] temp = {"users-friend"};
    firebase.deleteFromDatabase(temp, "kamrm");
    //firebase.testLoop();
    String[] data = {"PerfectDate", "Test-User"};
//    firebase.putDatabase(data, "embedding", "empty");
   List<List<Double>> testData2 = List.of(List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(17.5, 28.4, 3566.3), List.of(48.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),
           List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),
           List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),
           List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),
           List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),
           List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3),List.of(1.5, 2.4, 3.3), List.of(4.3, 5.3, 3.3));

    String testData = "[[Lorem ipsum dolor sit amet,],[id nec ferri dicta deterruisset, no vim sale laboramus efficiantur. Eligendi moderatius neglegentur his eu, eam novum bonorum ponderum ei, cum ut saperet salutatus vulputate. Alterum tractatos cum te, usu mucius veritus concludaturque at. Has ne veniam malorum scripserit. Ex ornatus consequat similique pro, dolores sadipscing ei nam.],[Esse minimum ad eum. Sed gubergren aliquando consetetur ut, mollis][aliquip scriptorem cu per. Natum mucius percipit sea at. Ei aperiri accusata per, pro ut accusata perpetua]," +
            "[Vidit denique commune ne ius, ei movet equidem vulputate vim. Nibh libris primis in mel, vix no nominati iudicabit, vidit movet patrioque id eos. At mea verterem tincidunt. Dicam pat][rioque est id, erat inermis eloquentiam ea vel, summo dolor luptatum eum an. Cum an utroque recteque maluisset, ut placerat aliquando quo, usu suas hendrerit at.]" +
            "[Eu sit legere labores. Eos definiebas elaboraret ne, ius mutat fugit definitiones et. Duo ut aliquid facilis fuisset, no graeco indoctum his. Enim utamur a],[ccusam sit et, ea maiorum vivendum vulputate pro. Impetus volumus ea vel, ei stet soluta vulputate sit.]," +
            "[Vis id tantas viderer neglegentur, ad congue nostrum officiis mea, nam ex affert maluisset. Feugait imperdiet vis ex, ex per solet nostrud. Ex e],[um erat assum. No vix eloquentiam mediocritatem, ex mei partem voluptua, sit alii consulatu et.]]";
    //firebase.exp(data, "embedding", testData2.toString());
    //firebase.exp("lots and lots and lots of data \nfhdkskdjnc");

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

    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Server started.");
  }



}
