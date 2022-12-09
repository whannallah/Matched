package edu.brown.cs.student.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.datasource.ExternalAPIHandler;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class QuestionnaireAPIHandler extends ExternalAPIHandler implements Route {
  FriendQuestionnaireResponse t;
  private FormData dataObject;

  /**
   * handler for the API of getting questionnaire data from front end
   * @param object
   */
  public QuestionnaireAPIHandler(FormData object){
    super();
    this.dataObject = object;
  }

  /**
   * Method that handles get request and outputs a serialized response.
   *
   * @param request - the request to handle
   * @param response - the response to modify
   * @return A serialized success response or error
   * @throws Exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    try {

      Moshi moshi = new Moshi.Builder().build();
      QueryParamsMap qm = request.queryMap();
      String data = qm.value("data-vals"); //json data of response answers
      System.out.println(data);
      String QType = qm.value("Qtype"); //questionnaire type
      System.out.println(QType);
      System.out.println(QType);
      this.dataObject.loadData(data);
      this.dataObject.dataLoaded(true);
      this.dataObject.setQtype(QType);
      System.out.println(dataObject.getBooleanLoaded());


      CohereAPIHandler handler = new CohereAPIHandler(dataObject); //api call to cohereAPI with shared dataObject
      handler.handle(request, response);
      Object toReturn = handler.firebase.getUsersToReturn();

      return this.serialize(toReturn);

    } catch (NullPointerException e) {
      return new ErrBadJsonResponse().serialize();
    } catch (Exception e) {
      // add message later
      e.printStackTrace();
      throw e;
    }
  }

  private String serialize(Object o) {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<Object> jsonAdapter = moshi.adapter(
        (Object.class));
    System.out.println(jsonAdapter.toJson(o));

    return jsonAdapter.toJson(o);
  }
}
