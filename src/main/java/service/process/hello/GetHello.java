package service.process.hello;

import com.google.gson.Gson;
import io.swagger.annotations.*;
import service.db.Datastore;
import service.process.api.ApiResult;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static spark.Spark.halt;

@Api
@Path("/hello")
@Produces("application/json")
public class GetHello implements Route {

    @Inject Gson gson;
    @Inject Datastore datastore;

    @GET
    @ApiOperation(value = "Get hello message", nickname = "GetHello")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = service.process.hello.Hello.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiResult.class),
            @ApiResponse(code = 404, message = "Not Found", response = ApiResult.class)
    })
    @Override
    public Object handle(@ApiParam(hidden = true) Request request,
                        @ApiParam(hidden = true) Response response)
            throws Exception
    {
        Object o = datastore.r();

        if (o == null) {
            halt(404, gson.toJson(ApiResult.of(404, "Message not found")));
        }

        return (Hello) o;
    }

}
