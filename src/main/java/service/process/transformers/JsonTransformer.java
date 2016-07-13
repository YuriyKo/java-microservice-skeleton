package service.process.transformers;

import com.google.gson.Gson;
import spark.ResponseTransformer;

import javax.inject.Inject;

public class JsonTransformer implements ResponseTransformer {

    @Inject
    Gson gson;

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}