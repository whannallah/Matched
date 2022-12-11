package edu.brown.cs.student.server;

import com.squareup.moshi.Moshi;
import edu.brown.cs.student.datasource.ExternalAPIHandler;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetMatchesAPIHandler extends ExternalAPIHandler implements Route {

    private final Firebase firebase;

    public GetMatchesAPIHandler(Firebase firebase) {
        super();
        this.firebase = firebase;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Moshi moshi = new Moshi.Builder().build();
        QueryParamsMap qm = request.queryMap();
        String userKey = qm.value("user-key");

        String QType = qm.value("Qtype"); //questionnaire type
        String[] temp = {QType, userKey};
        String userData = this.firebase.readDatabase(temp);
        User mainUser = moshi.adapter(User.class).fromJson(userData);
        firebase.loop(QType, mainUser);
        return null;
    }
}
