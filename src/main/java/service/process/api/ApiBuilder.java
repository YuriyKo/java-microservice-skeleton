package service.process.api;

import com.google.inject.Injector;
import io.swagger.annotations.Api;
import org.reflections.Reflections;
import spark.ResponseTransformer;
import spark.Route;
import spark.Service;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.lang.reflect.Method;
import java.util.Set;

public class ApiBuilder {

    private Service s;
    private ResponseTransformer transformer;
    private Injector injector;

    @Inject
    public ApiBuilder(Service s, ResponseTransformer transformer, Injector injector) {
        this.s = s;
        this.transformer = transformer;
        this.injector = injector;
    }

    public void setupRoutes(String packageName) throws InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> apiRoutes = reflections.getTypesAnnotatedWith(Api.class);

        for (Class<?> clazz : apiRoutes) {
            Route sparkRoute = (Route) injector.getInstance(clazz);
            Path path = clazz.getAnnotation(Path.class);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String friendlyRoute = path.value().replaceAll("\\{(.*?)\\}", ":$1");

                boolean transform = method.getAnnotation(NoTransform.class) == null ? Boolean.TRUE : Boolean.FALSE;

                POST post = method.getAnnotation(POST.class);
                if (post != null) {
                    if (transform) {
                        s.post(friendlyRoute, sparkRoute, transformer);
                    } else {
                        s.post(friendlyRoute, sparkRoute);
                    }
                    break;
                }

                GET get = method.getAnnotation(GET.class);
                if (get != null) {
                    if (transform) {
                        s.get(friendlyRoute, sparkRoute, transformer);
                    } else {
                        s.get(friendlyRoute, sparkRoute);
                    }
                    break;
                }

                DELETE delete = method.getAnnotation(DELETE.class);
                if (delete != null) {
                    if (transform) {
                        s.delete(friendlyRoute, sparkRoute, transformer);
                    } else {
                        s.delete(friendlyRoute, sparkRoute);
                    }
                    break;
                }

                PUT put = method.getAnnotation(PUT.class);
                if (put != null) {
                    if (transform) {
                        s.put(friendlyRoute, sparkRoute, transformer);
                    } else {
                        s.put(friendlyRoute, sparkRoute);
                    }
                    break;
                }
            }
        }
    }

}
