package service.process.filters;

import com.google.gson.Gson;
import service.process.api.ApiResult;
import spark.Request;
import spark.Response;

import javax.inject.Inject;

import static spark.Spark.halt;

public class BeforeImpl implements Before {

    @Inject
    Gson gson;

    @Override
    public void handle(Request request, Response response) throws Exception {
        String auth = request.headers("auth");

/*
        if (auth == null || auth.isEmpty()) {
            halt(401, gson.toJson(ApiResult.of(401, "Unauthorized")));
        }
*/
    }

}
