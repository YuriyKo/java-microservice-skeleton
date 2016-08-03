package service.process.index;

import spark.Request;
import spark.Response;
import spark.Route;

public class IndexRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/hello");
        return null;
    }

}
