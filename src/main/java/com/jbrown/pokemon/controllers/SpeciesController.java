package com.jbrown.pokemon.controllers;

import com.google.gson.Gson;
import com.google.common.collect.ImmutableSet;
import com.jbrown.pokemon.entities.silver.Species;

public class SpeciesController {
    private static Gson gson = new Gson();

    public static String getAllSpecies() {
        ImmutableSet<Species> allSpecies = Species.values();
        return gson.toJson(allSpecies);
    }
}
