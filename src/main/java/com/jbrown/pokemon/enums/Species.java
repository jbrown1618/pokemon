package com.jbrown.pokemon.enums;

import com.jbrown.pokemon.entities.Stats;
import static com.jbrown.pokemon.enums.Type.*;

public enum Species {
    BULBASAUR(1, "Bulbasaur", GRASS, null),
    IVYSAUR(2, "Ivysaur", GRASS, POISON),
    VENUSAUR(3, "Venusaur", GRASS, POISON);

    private int number;
    private String name;
    private Type type1;
    private Type type2;
    private Stats baseStats;

    Species(int number, String name, Type type1, Type type2) {
        this.number = number;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }
}
