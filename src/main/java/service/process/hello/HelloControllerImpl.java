package service.process.hello;

import spark.Request;
import spark.Response;

public class HelloControllerImpl implements HelloController {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        return HelloMessage.builder().text("Hello World!").build();
    }
}
