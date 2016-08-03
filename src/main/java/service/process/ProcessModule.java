package service.process;

import com.google.inject.AbstractModule;
import service.process.api.ApiBuilder;
import service.process.filters.After;
import service.process.filters.AfterImpl;
import service.process.filters.Before;
import service.process.filters.BeforeImpl;
import service.process.index.IndexRoute;
import service.process.transformers.JsonTransformer;
import spark.ResponseTransformer;

public class ProcessModule extends AbstractModule {

    protected void configure() {
        bind(ProcessService.class).to(ProcessServiceImpl.class).asEagerSingleton();
        // depends on GsonModule
        bind(ResponseTransformer.class).to(JsonTransformer.class).asEagerSingleton();

        bind(Before.class).to(BeforeImpl.class).asEagerSingleton();
        bind(After.class).to(AfterImpl.class).asEagerSingleton();

        bind(IndexRoute.class).asEagerSingleton();
        bind(ApiBuilder.class).asEagerSingleton();
    }

}
