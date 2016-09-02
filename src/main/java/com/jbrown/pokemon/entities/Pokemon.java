package com.jbrown.pokemon.entities;

import com.jbrown.pokemon.enums.Species;

public class Pokemon {
    private Species species;
    private String nickname;
    private int level;
    private Stats currentStats;
    private Stats evStats;
    private Stats ivStats;

    public Pokemon (Species species, int level, Stats currentStats) {
        this.species = species;
        this.level = level;
        this.currentStats = currentStats;
    }

    public Species getSpecies() {
        return species;
    }

    public int getLevel() {
        return level;
    }

    public Stats getCurrentStats() {
        return currentStats;
    }

    public void setCurrentStats(Stats currentStats) {
        this.currentStats = currentStats;
    }
}
