package com.jbrown.pokemon.enums;

import com.jbrown.pokemon.entities.Stats;
import static com.jbrown.pokemon.enums.Type.*;

public enum Species {
    BULBASAUR(1, "Bulbasaur", GRASS, null),
    IVYSAUR(2, "Ivysaur", GRASS, POISON),
    VENUSAUR(3, "Venusaur", GRASS, POISON),
    CHARMANDER(4, "Charmander", FIRE, null),
    CHARMELEON(5, "Charmeleon", FIRE, null),
    CHARIZARD(6, "Charizard", FIRE, FLYING),
    SQUIRTLE(7, "Squirtle", WATER, null),
    WARTORTLE(8, "Wartortle", WATER, null),
    BLASTOISE(9, "Blastoise", WATER, null);

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

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Type getType1() {
        return type1;
    }

    public Type getType2() {
        return type2;
    }

    public Stats getBaseStats() {
        return baseStats;
    }
}
