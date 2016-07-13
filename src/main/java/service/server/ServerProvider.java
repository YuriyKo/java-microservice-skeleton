package service.server;

import service.config.AppConfig;
import com.google.inject.Provider;
import spark.Service;

import javax.inject.Inject;

public class ServerProvider implements Provider<Service> {

    @Inject
    AppConfig cfg;

    private static Service service;

    public Service get() {
        if (service == null) {
            service = Service.ignite();

            int port = cfg.getPort();
            if (port != 0) {
                service.port(port);
            }

            service.staticFileLocation(cfg.getStaticFileLocation());
        }

        return service;
    }

}
