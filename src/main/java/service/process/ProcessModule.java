package service.process;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import service.process.filters.After;
import service.process.filters.AfterImpl;
import service.process.filters.Before;
import service.process.filters.BeforeImpl;
import service.process.hello.HelloController;
import service.process.hello.HelloControllerImpl;
import service.process.index.IndexController;
import service.process.index.IndexControllerImpl;
import service.process.transformers.GsonProvider;
import service.process.transformers.JsonTransformer;
import spark.ResponseTransformer;

public class ProcessModule extends AbstractModule {

    protected void configure() {
        bind(ProcessService.class).to(ProcessServiceImpl.class).asEagerSingleton();
        bind(ResponseTransformer.class).to(JsonTransformer.class).asEagerSingleton();
        bind(Gson.class).toProvider(GsonProvider.class);

        bind(Before.class).to(BeforeImpl.class).asEagerSingleton();
        bind(After.class).to(AfterImpl.class).asEagerSingleton();
        bind(IndexController.class).to(IndexControllerImpl.class).asEagerSingleton();
        bind(HelloController.class).to(HelloControllerImpl.class).asEagerSingleton();
    }

}
