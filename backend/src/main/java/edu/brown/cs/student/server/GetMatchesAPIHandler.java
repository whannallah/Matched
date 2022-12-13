package edu.brown.cs.student.server;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import edu.brown.cs.student.datasource.ExternalAPIHandler;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class GetMatchesAPIHandler extends ExternalAPIHandler implements Route {

    private final Firebase firebase;
    private List<User> topMatches;

    public GetMatchesAPIHandler(Firebase firebase) {
        super();
        this.firebase = firebase;
        this.topMatches = new ArrayList<>();

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        this.topMatches.clear();
        QueryParamsMap qm = request.queryMap();
        String userKey = qm.value("user-key");

        String QType = qm.value("Qtype"); //questionnaire type
        this.topMatches = firebase.otherLoop(QType, userKey);
        for (User match: topMatches) {
            System.out.println(match.getName());
            System.out.println("this is the match list above:");

        }
        System.out.println("all the matches");
        return this.serialize(topMatches);
    }

    private String serialize(Object o) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Object> jsonAdapter = moshi.adapter(
            (Object.class));

        return jsonAdapter.toJson(o);
    }
}