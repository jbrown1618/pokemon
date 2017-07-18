package com.jbrown.pokemon.config;

import com.jbrown.pokemon.controller.BattleController;
import com.jbrown.pokemon.controller.SpeciesController;
import com.jbrown.pokemon.controller.transformer.JsonResponseTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
public class RouteConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteConfiguration.class);

    @Autowired
    SpeciesController speciesController;

    @Autowired
    BattleController battleController;

    @Autowired
    JsonResponseTransformer jsonResponseTransformer;

    public void configure() {
        staticFileLocation("/public");

        before("/api/*", (request, response) -> {
            LOGGER.info(request.url());
            response.type("application/json");
        });

        redirect.get("/", "/index.html"); // Serve up main page

        get("/api/species", speciesController::getAllSpecies, jsonResponseTransformer);
        get("/api/species/:id", speciesController::getSpecies, jsonResponseTransformer);
        post("/api/battle/next-state", battleController::getNextState, jsonResponseTransformer);
    }
}
