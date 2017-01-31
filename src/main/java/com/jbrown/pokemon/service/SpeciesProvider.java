package com.jbrown.pokemon.service;

import com.jbrown.pokemon.entities.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class SpeciesProvider {

    @Autowired
    private Set<Species> allSpecies;

    @PostConstruct
    private void init() {
        if (allSpecies.size() == 0) {
            throw new IllegalStateException("No species are configured");
        }
    }

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
