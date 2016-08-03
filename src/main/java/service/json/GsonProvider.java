package service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provider;

public class GsonProvider implements Provider<Gson> {

    public Gson get() {
        // if you need a customized Gson object - do it here
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        return gson;
    }

}
