package edu.brown.cs.student.server;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.datasource.ExternalAPIHandler;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CohereAPIHandler extends ExternalAPIHandler implements Route {

  private FormData dataObject;

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
      Moshi moshi = new Moshi.Builder().build();
      QuestionairreResponse t = moshi.adapter(QuestionairreResponse.class).fromJson(dataJson);

      try {
        System.out.println(t.getDreamVac());

        // first external http request
        String CohereResponseJson =
            this.externalPost("https://api.cohere.ai/embed", "{\"texts\":[\"" + t.getDreamVac() + "\",\"" + t.getHobby() +"\"]}");

        Moshi moshi2 = new Moshi.Builder().build();
        CohereResponse CohereReturn =
            moshi2.adapter(CohereResponse.class).fromJson(CohereResponseJson);
        List<List<Float>> embeddings = CohereReturn.getEmbeddings();

        System.out.println(embeddings.get(0).get(0));

        // embedding will need to be written to database, along with unique identifier, name, year, etc
        return new CohereAPIHandler.CohereSuccessResponse(embeddings).serialize();


      } catch (NullPointerException e) {
        // if pr.properties is null or fr.properties is null
        // i.e. an error from NWS API with points or gridpoints request
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
