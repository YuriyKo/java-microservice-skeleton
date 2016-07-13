package service.config;

import lombok.Getter;
import net.jmob.guice.conf.core.BindConfig;
import net.jmob.guice.conf.core.InjectConfig;

@BindConfig(value = "app")
public class AppConfig {

    @InjectConfig(value = "port")
    @Getter
    private Integer port = 4567;

    @InjectConfig(value = "staticFileLocation")
    @Getter
    private String staticFileLocation = "/public";

}
