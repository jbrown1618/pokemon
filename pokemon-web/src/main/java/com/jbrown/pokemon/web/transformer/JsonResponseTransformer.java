package com.jbrown.pokemon.web.transformer;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import spark.ResponseTransformer;

@Component
public class JsonResponseTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
