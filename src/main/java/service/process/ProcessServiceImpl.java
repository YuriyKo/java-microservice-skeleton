package service.process;

import service.process.filters.After;
import service.process.filters.Before;
import service.process.hello.HelloController;
import service.process.index.IndexController;
import service.server.ServerProvider;
import spark.ResponseTransformer;
import spark.Service;

import javax.inject.Inject;

public class ProcessServiceImpl implements ProcessService {

    @Inject
    ServerProvider server;
    @Inject
    ResponseTransformer transformer;
    @Inject
    Before before;
    @Inject
    After after;
    @Inject
    HelloController helloController;
    @Inject
    IndexController indexController;

    @Override
    public void start() {
        Service s = server.get();

        // Set up before-filters (called before each requests)
        s.before("*", before);

        // Routes
        s.get("/", indexController);
        s.get("/hello", helloController, transformer);


        //Set up after-filters (called after each requests)
        s.after("*", after);
    }

}
