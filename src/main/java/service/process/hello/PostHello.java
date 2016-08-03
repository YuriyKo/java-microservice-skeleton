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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static spark.Spark.halt;

@Api
@Path("/hello")
@Produces("application/json")
public class PostHello implements Route {

    @Inject Gson gson;
    @Inject Datastore datastore;

    @POST
    @ApiOperation(value = "Create hello message", nickname = "CreateHello")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header"),
            @ApiImplicitParam(required = true, dataType = "service.process.hello.Hello", paramType = "body")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResult.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiResult.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiResult.class)
    })
    @Override
    public Object handle(@ApiParam(hidden = true) Request request,
                         @ApiParam(hidden = true) Response response)
            throws Exception
    {
        Hello o = null;
        try {
            String json = request.body();
            o = gson.fromJson(json, Hello.class);
        } catch (JsonSyntaxException e) {
            halt(400, gson.toJson(ApiResult.of(400, "Invalid input data")));
        }

        datastore.c(o);

        response.status(201);
        return ApiResult.of(201, "Created");
    }

}
