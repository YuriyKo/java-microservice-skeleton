package service.process;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import service.process.api.ApiBuilder;
import service.process.api.SwaggerParser;
import service.process.filters.After;
import service.process.filters.Before;
import service.process.index.IndexRoute;
import service.server.ServerProvider;
import spark.Service;

import javax.inject.Inject;

@SwaggerDefinition(
        host = "localhost:9876",
        info = @Info(
                description = "Java Microservice Skeleton v.3.0",
                version = "V3.0",
                title = "Microservice API",
                contact = @Contact(
                        name = "Yuriy Kozhynov",
                        url = "https://github.com/ipkeys/eiss")
        ),
        schemes = {
                SwaggerDefinition.Scheme.HTTP,
                SwaggerDefinition.Scheme.HTTPS
        },
        consumes = {
                "application/json"
        },
        produces = {
                "application/json"
        },
        tags = {
                @Tag(name = "swagger")
        }
)

@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Inject
    ServerProvider server;
    @Inject
    Before before;
    @Inject
    After after;
    @Inject
    IndexRoute indexRoute;
    @Inject
    ApiBuilder apiBuilder;

    private final static String API_PACKAGE = "service.process";

    @Override
    public void start() {
        Service s = server.get();

        // Set up before-filters (called before each requests)
        s.before("*", before);

        // Routes
        try {
            s.get("/", indexRoute);
            apiBuilder.setupRoutes(API_PACKAGE);
            s.get("/api", (req, res) -> SwaggerParser.getSwaggerJson(API_PACKAGE));
        } catch (Exception e) {
            log.error("Cannot build routers - {}", e.getMessage());
        }

        // Set up after-filters (called after each requests)
        s.after("*", after);

        // Enables CORS on requests
        enableCORS(s, "*", "*", "*");

        log.info("Process service is ready");
    }


    private void enableCORS(Service s, final String origin, final String methods, final String headers) {
        s.options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");

            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        s.before((req, res) -> {
            res.header("Access-Control-Allow-Origin", origin);
            res.header("Access-Control-Request-Method", methods);
            res.header("Access-Control-Allow-Headers", headers);
        });
    }

}
