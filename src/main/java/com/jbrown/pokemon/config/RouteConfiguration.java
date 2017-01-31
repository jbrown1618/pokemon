package com.jbrown.pokemon.config;

import com.google.gson.Gson;
import com.jbrown.pokemon.controllers.BattleController;
import com.jbrown.pokemon.controllers.SpeciesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

@Component
public class RouteConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteConfiguration.class);

    @Autowired
    SpeciesController speciesController;

    @Autowired
    BattleController battleController;

    public void configureRoutes() {
        Gson gson = new Gson();

        port(8080);

        staticFileLocation("/public");

        before("/api/*", (request, response) -> {
            LOGGER.info(request.url());
            response.type("application/json");
        });

        redirect.get("/", "/index.html"); // Serve up main page

        get("/api/species", speciesController::getAllSpecies, gson::toJson);
        get("/api/species/:id", speciesController::getSpecies, gson::toJson);
        post("/api/battle/next-state", battleController::getNextState, gson::toJson);
    }
}
