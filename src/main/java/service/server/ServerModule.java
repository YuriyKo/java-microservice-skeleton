package service.server;

import com.google.inject.AbstractModule;
import spark.Service;

public class ServerModule extends AbstractModule {

    protected void configure() {
        bind(Service.class).toProvider(ServerProvider.class);
    }

}
