package com.jbrown.pokemon.service;

import com.jbrown.pokemon.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpeciesProvider {

    @Autowired
    private Set<Species> allSpecies;

    public Species getSpecies(int id) {
        return allSpecies.stream()
            .filter(species -> species.getNumber() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No species exists with id " + id));
    }

    public Set<Species> getAllSpecies() {
        return allSpecies;
    }
}
