package service.process.index;

import spark.Request;
import spark.Response;

public class IndexControllerImpl implements IndexController {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/hello");
        return null;
    }

}
