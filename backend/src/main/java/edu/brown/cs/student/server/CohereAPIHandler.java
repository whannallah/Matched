package edu.brown.cs.student.server;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.datasource.ExternalAPIHandler;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CohereAPIHandler extends ExternalAPIHandler implements Route {

  private FormData dataObject;
  private String CohereResponseJson;
  private FriendQuestionnaireResponse t;

  /**
   * Constructor of CohereAPIHandler.
   * takes in the responses of a user
   */
  public CohereAPIHandler(FormData data) {
    super();
    this.dataObject = data;
  }

  /**
   * Method that handles get request and outputs a serialized response.
   *
   * @param request  - the request to handle
   * @param response - the response to modify
   * @return A serialized success response or error
   * @throws Exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    System.out.println("loaded");

    if(this.dataObject.getBooleanLoaded())
    {
      String dataJson =  this.dataObject.returnData();
      String Qtype = this.dataObject.getQtype();

      Moshi moshi = new Moshi.Builder().build();

      if (Qtype.equals("friend")){
        t = moshi.adapter(FriendQuestionnaireResponse.class).fromJson(dataJson);
      }

      //parses the data into QuestionaireResponse class so that individual answers can be passed
      //in to the cohereAPI call

      try {
        System.out.println(t.getDreamVac());


        //add if statemts for cohereapi call depending on type

        if (Qtype.equals("friend")){
          CohereResponseJson =
              this.externalPost("https://api.cohere.ai/embed", "{\"texts\":[\"" + t.getDreamVac() + "\",\"" + t.getHobby() +"\"]}");
          //api call where individual answers are passed in
        }

        if (Qtype.equals("date")){
          //change for date questions
          CohereResponseJson =
              this.externalPost("https://api.cohere.ai/embed", "{\"texts\":[\"" + t.getDreamVac() + "\",\"" + t.getHobby() +"\"]}");
          //api call where individual answers are passed in
        }

        if (Qtype.equals("study")){
          //change for study questions
          CohereResponseJson =
              this.externalPost("https://api.cohere.ai/embed", "{\"texts\":[\"" + t.getDreamVac() + "\",\"" + t.getHobby() +"\"]}");
          //api call where individual answers are passed in
        }

        // first external http request


        Moshi moshi2 = new Moshi.Builder().build();
        CohereResponse CohereReturn =
            moshi2.adapter(CohereResponse.class).fromJson(CohereResponseJson);
        List<List<Float>> embeddings = CohereReturn.getEmbeddings(); //vector embedding of semantic meaning of text

        System.out.println(embeddings.get(0).get(0));

        //creating a user to add to the database
        User userToDatabase = new User(Qtype, t.getName(),t.getPronouns(),t.getClassYear(),t.getEmail(),embeddings);

        Firebase firebase = new Firebase();
        firebase.initFirebase();
        String[] userRoot = {"users"};
        //adding user to database
        firebase.putDatabase(userRoot, userToDatabase.getEmailWithoutEdu(), firebase.createNewUser(userToDatabase));

        return new CohereAPIHandler.CohereSuccessResponse(embeddings).serialize();


      } catch (NullPointerException e) {
        return new ErrBadJsonResponse().serialize();
      } catch (Exception e) {
        // add message later
        e.printStackTrace();
        throw e;
      }


    }
    else {
      return null; //need to fix this
    }

  }

  /**
   * Response object to send, containing success notification and data
   */
  public record CohereSuccessResponse(String result, List<List<Float>> embeddings) {
    public CohereSuccessResponse(List<List<Float>> embeddings) {
      this("success", embeddings);
    }

    /**
     * @return this response, serialized as Json
     */
    String serialize() {
      try {
        // dependency injection to allow unit testing with mocks
        return CohereAPIUtilities.toJson(this);
      } catch (Exception e) {
        e.printStackTrace();
        throw e;
      }
    }
  }
}
