import service.config.ConfigModule;
import service.server.ServerModule;
import service.process.ProcessModule;
import service.process.ProcessService;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String ... args) {
        log.info("Start App");

        App app = new App();
        app.start();
    }

    private void start() {
        Injector injector = Guice.createInjector(
                new ConfigModule(),
                new ServerModule(),
                new ProcessModule()
        );

        ProcessService process = injector.getInstance(ProcessService.class);
        process.start();
    }

}
