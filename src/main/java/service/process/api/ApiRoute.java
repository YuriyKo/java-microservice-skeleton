package service.process.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;
import org.reflections.Reflections;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;

@Api(hidden = true)
@Path("/api")
@Produces("application/json")
public class ApiRoute implements Route {

    @Inject @Named("api_package")
    private String API_PACKAGE;

    @GET
    @NoTransform
    @Override
    public Object handle(Request request, Response response) throws Exception {

        Reflections reflections = new Reflections(API_PACKAGE);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setResourcePackage(API_PACKAGE);
        beanConfig.setScan(true);
        beanConfig.scanAndRead();
        Swagger swagger = beanConfig.getSwagger();

        Reader reader = new Reader(swagger);

        Set<Class<?>> apiClasses = reflections.getTypesAnnotatedWith(Api.class);

        // Gson seems cannot serialize Swagger to json. Will use Jackson here and @NoTransform
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return objectMapper.writeValueAsString(reader.read(apiClasses));
    }

}
