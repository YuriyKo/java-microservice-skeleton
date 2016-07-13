package service.config;

import com.google.inject.AbstractModule;
import net.jmob.guice.conf.core.ConfigurationModule;

public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        install(ConfigurationModule.create());
        requestInjection(AppConfig.class);
    }

}