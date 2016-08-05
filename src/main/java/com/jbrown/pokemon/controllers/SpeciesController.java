package com.jbrown.pokemon.controllers;

import com.google.gson.Gson;
import com.jbrown.pokemon.enums.Species;

public class SpeciesController {
    private static Gson gson = new Gson();

    public static String getAllSpecies() {
        Species[] allSpecies = Species.values();
        return gson.toJson(allSpecies);
    }
}
