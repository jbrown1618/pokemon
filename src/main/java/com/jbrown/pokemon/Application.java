package com.jbrown.pokemon;

import com.jbrown.pokemon.controllers.SpeciesController;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(1618);
        staticFileLocation("/public");
        get("/hello", (req, res) -> "Hello World");
        get("/species", (req,res) -> SpeciesController.getAllSpecies());
    }
}
