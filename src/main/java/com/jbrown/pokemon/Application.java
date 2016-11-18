package com.jbrown.pokemon;

import com.google.gson.Gson;
import com.jbrown.pokemon.controllers.BattleController;
import com.jbrown.pokemon.controllers.SpeciesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Gson gson = new Gson();

        port(8080);

        staticFileLocation("/public");

        before("/api/*", (request, response) -> {
            LOGGER.info(request.url());
            response.type("application/json");
        });

        get("/api/species", SpeciesController.getAllSpecies, gson::toJson);
        get("/api/species/:id", SpeciesController.getSpecies, gson::toJson);
        post("/api/battle/next-state", BattleController.getNextState, gson::toJson);

    }
}
