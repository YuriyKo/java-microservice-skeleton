package service.process.filters;

import spark.Request;
import spark.Response;

public class AfterImpl implements After {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.header("Content-Encoding", "gzip");
    }

}
