package service.process.hello;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.swagger.annotations.*;
import service.db.Datastore;
import service.process.api.ApiResult;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static spark.Spark.halt;

@Api
@Path("/hello")
@Produces("application/json")
public class PutHello implements Route {

    @Inject Gson gson;
    @Inject Datastore datastore;

    @PUT
    @ApiOperation(value = "Update hello message", nickname = "UpdateHello")
    @ApiImplicitParams({
        @ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header"),
        @ApiImplicitParam(required = true, dataType = "service.process.hello.Hello", paramType = "body")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ApiResult.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ApiResult.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ApiResult.class)
    })
    @Override
    public Object handle(@ApiParam(hidden = true) Request request,
                         @ApiParam(hidden = true) Response response)
        throws Exception
    {
        Hello o;

        try {
            String json = request.body();
            o = gson.fromJson(json, Hello.class);
        } catch (JsonSyntaxException e) {
            response.status(400);
            return ApiResult.of(400, "Invalid input data");
        }

        datastore.u(o);

        response.status(200);
        return ApiResult.of(200, "OK");
    }

}
