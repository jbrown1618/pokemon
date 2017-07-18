package com.jbrown.pokemon.service;

import com.jbrown.pokemon.domain.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConfiguredSpeciesProvider implements SpeciesProvider {

    @Autowired
    private Set<Species> allSpecies;

    @Override
    public Species getSpecies(int id) {
        return allSpecies.stream()
            .filter(species -> species.getNumber() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No species exists with id " + id));
    }

    @Override
    public Set<Species> getAllSpecies() {
        return allSpecies;
    }
}
