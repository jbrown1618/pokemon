package com.jbrown.pokemon;

import com.google.gson.Gson;
import com.jbrown.pokemon.controllers.BattleController;
import com.jbrown.pokemon.controllers.SpeciesController;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        Gson gson = new Gson();

        port(8080);

        staticFileLocation("/public");

        get("/pokedex/list", SpeciesController.getAllSpecies, gson::toJson);
        post("/battle/next-state", BattleController.getNextState, gson::toJson);
    }
}
