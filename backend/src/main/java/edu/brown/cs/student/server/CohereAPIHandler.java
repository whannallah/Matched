package edu.brown.cs.student.server;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.datasource.ExternalAPIHandler;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CohereAPIHandler extends ExternalAPIHandler implements Route {

  private FormData dataObject;
  private String CohereResponseJson;
  Firebase firebase = new Firebase();

  private static Object LOCK = new Object();

  /**
   * Constructor of CohereAPIHandler.
   * takes in the responses of a user
   */
  public CohereAPIHandler(FormData data) throws IOException {
    super();
    this.dataObject = data;
    //firebase.unInitFirebase();


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

      String dataJson = this.dataObject.returnData();
      String Qtype = this.dataObject.getQtype();
      System.out.println(Qtype);

      Moshi moshi = new Moshi.Builder().build();

      if (Qtype.equals("friend")) {
        FriendQuestionnaireResponse t =
            moshi.adapter(FriendQuestionnaireResponse.class).fromJson(dataJson);

        try {
          System.out.println(t.getDreamVac());
          CohereResponseJson =
              this.externalPost("https://api.cohere.ai/embed",
                  "{\"texts\":[\"" + t.getPerfSat() + "\",\"" + t.getDreamVac() + "\",\"" + t.getHobby() + "\",\"" + t.getReasoning() + "\"]}");
          //api call where individual answers are passed in

          Moshi moshi2 = new Moshi.Builder().build();
          CohereResponse CohereReturn =
              moshi2.adapter(CohereResponse.class).fromJson(CohereResponseJson);
          List<List<Float>> embeddings =
              CohereReturn.getEmbeddings(); //vector embedding of semantic meaning of text

          System.out.println(embeddings.get(0).get(0));

          List<Float> singleList1 = new ArrayList();
          singleList1.add(Float.parseFloat("1.3"));
          singleList1.add(Float.parseFloat("2.3"));
          List<Float> singleList2 = new ArrayList();
          singleList2.add(Float.parseFloat("3.3"));
          singleList2.add(Float.parseFloat("4.3"));
          List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
          exampleEmbedding.add(singleList1);
          exampleEmbedding.add(singleList2);

          //creating a user to add to the database
          User userToDatabase =
              new User(Qtype, t.getName(), t.getPronouns(), t.getClassYear(), t.getEmail(),
                  embeddings); //should be embeddings

          String[] userRoot = {"users-friend"};

          firebase.initFirebase();
          //adding user to database
          firebase.putDatabase(userRoot, userToDatabase.getEmailWithoutEdu(),
              firebase.createNewUser(userToDatabase));

          firebase.loop("users-friend", userToDatabase);

          //Thread.sleep(10000);
          String[] place = {"users-friend-matches"};
          List toReturn = new LinkedList();
          for (int i=0; i<2;i++){
            toReturn.add(firebase.createNewUser(firebase.getUsersToReturn().get(i)));
          }
          firebase.putDatabase(place, userToDatabase.getEmailWithoutEdu(), toReturn);


          return new CohereAPIHandler.CohereSuccessResponse(embeddings).serialize();

        } catch (NullPointerException e) {
          return new ErrBadJsonResponse().serialize();
        } catch (Exception e) {
          // add message later
          e.printStackTrace();
          throw e;
        }
      }

    if (Qtype.equals("date")) {
      DateQuestionnaireResponse t =
          moshi.adapter(DateQuestionnaireResponse.class).fromJson(dataJson);

      try {
        System.out.println(t.getPerfDate());
        CohereResponseJson =
            this.externalPost("https://api.cohere.ai/embed",
                "{\"texts\":[\"" + t.getPerfDate() + "\",\"" + t.getPassions() + "\",\"" + t.getExpectations() + "\",\"" + t.getReasoning() + "\"]}");
        //api call where individual answers are passed in

        List<Float> singleList1 = new ArrayList();
        singleList1.add(Float.parseFloat("1.3"));
        singleList1.add(Float.parseFloat("2.3"));
        List<Float> singleList2 = new ArrayList();
        singleList2.add(Float.parseFloat("3.3"));
        singleList2.add(Float.parseFloat("4.3"));
        List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
        exampleEmbedding.add(singleList1);
        exampleEmbedding.add(singleList2);

        Moshi moshi2 = new Moshi.Builder().build();
        CohereResponse CohereReturn =
            moshi2.adapter(CohereResponse.class).fromJson(CohereResponseJson);
        List<List<Float>> embeddings =
            CohereReturn.getEmbeddings(); //vector embedding of semantic meaning of text

        System.out.println(embeddings.get(0).get(0));

        //creating a user to add to the database
        User userToDatabase =
            new User(Qtype, t.getName(), t.getPronouns(), t.getClassYear(), t.getEmail(),
                exampleEmbedding);

        firebase.initFirebase();
        String[] userRoot = {"users-date"};
        //adding user to database
        firebase.putDatabase(userRoot, userToDatabase.getEmailWithoutEdu(),
            firebase.createNewUser(userToDatabase));

        System.out.println("got into date");
        firebase.loop("users-date", userToDatabase);
        System.out.println("exited loop");

        //Thread.sleep(10000);
        //LOCK.wait();
        String[] place = {"users-date-matches"};

        List toReturn = new LinkedList();

        //have it as top 2 users right now
        for (int i=0; i<2;i++){
          toReturn.add(firebase.createNewUser(firebase.getUsersToReturn().get(i)));
        }


        firebase.putDatabase(place, userToDatabase.getEmailWithoutEdu(), toReturn);
        //firebase.unInitFirebase();

        return new CohereAPIHandler.CohereSuccessResponse(embeddings).serialize();

      } catch (NullPointerException e) {
        return new ErrBadJsonResponse().serialize();
      } catch (Exception e) {
        // add message later
        e.printStackTrace();
        throw e;
      }
    }

    if (Qtype.equals("study")) {
      StudyQuestionnaireResponse t =
          moshi.adapter(StudyQuestionnaireResponse.class).fromJson(dataJson);

      try {
        System.out.println(t.getStudyHabs());
        CohereResponseJson =
            this.externalPost("https://api.cohere.ai/embed",
                "{\"texts\":[\"" + t.getStudyHabs() + "\",\"" + t.getClasses() + "\",\"" + t.getStudySpot() + "\",\"" + t.getReasoning() + "\"]}");
        //api call where individual answers are passed in

        Moshi moshi2 = new Moshi.Builder().build();
        CohereResponse CohereReturn =
            moshi2.adapter(CohereResponse.class).fromJson(CohereResponseJson);
        List<List<Float>> embeddings =
            CohereReturn.getEmbeddings(); //vector embedding of semantic meaning of text

        System.out.println(embeddings.get(0).get(0));

        List<Float> singleList1 = new ArrayList();
        singleList1.add(Float.parseFloat("1.3"));
        singleList1.add(Float.parseFloat("2.3"));
        List<Float> singleList2 = new ArrayList();
        singleList2.add(Float.parseFloat("3.3"));
        singleList2.add(Float.parseFloat("4.3"));
        List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
        exampleEmbedding.add(singleList1);
        exampleEmbedding.add(singleList2);

        //creating a user to add to the database
        User userToDatabase =
            new User(Qtype, t.getName(), t.getPronouns(), t.getClassYear(), t.getEmail(),
                exampleEmbedding);

        String[] userRoot = {"users-study"};
        //adding user to database
        firebase.initFirebase();
        firebase.putDatabase(userRoot, userToDatabase.getEmailWithoutEdu(),
            firebase.createNewUser(userToDatabase));

        firebase.loop("users-study", userToDatabase);
        System.out.println("exited loop");

        //Thread.sleep(10000);
        String[] place = {"users-study-matches"};
        List toReturn = new LinkedList();
        for (int i=0; i<2;i++){
          toReturn.add(firebase.createNewUser(firebase.getUsersToReturn().get(i)));
        }

        firebase.putDatabase(place, userToDatabase.getEmailWithoutEdu(), toReturn);

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
      return "invalid Query param"; //this case should never be reached
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
