package com.jbrown.pokemon.service;

import com.jbrown.pokemon.model.Species;

import java.util.Set;

public interface SpeciesProvider {
    Species getSpecies(int id);

    Set<Species> getAllSpecies();
}
