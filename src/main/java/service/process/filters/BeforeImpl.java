package service.process.filters;

import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class BeforeImpl implements Before {

    @Override
    public void handle(Request request, Response response) throws Exception {
        boolean authenticated = Boolean.TRUE;

        if (!authenticated) {
            halt(401, "You are not welcome here");
        }

    }

}
