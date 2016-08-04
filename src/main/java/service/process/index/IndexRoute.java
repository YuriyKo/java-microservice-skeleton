package service.process.index;

import io.swagger.annotations.Api;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Api(hidden = true)
@Path("/")
public class IndexRoute implements Route {

    @GET
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/hello");
        return null;
    }

}
