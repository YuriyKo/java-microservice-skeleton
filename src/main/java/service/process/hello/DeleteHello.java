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
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static spark.Spark.halt;

@Api
@Path("/hello")
@Produces("application/json")
public class DeleteHello implements Route {

    @Inject Gson gson;
    @Inject Datastore datastore;

    @DELETE
    @ApiOperation(value = "Delete hello message", nickname = "DeleteHello")
    @ApiImplicitParams({
        @ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ApiResult.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ApiResult.class)
    })
    @Override
    public Object handle(@ApiParam(hidden = true) Request request,
                         @ApiParam(hidden = true) Response response)
        throws Exception
    {
        datastore.d();

        response.status(200);
        return ApiResult.of(200, "OK");
    }

}
