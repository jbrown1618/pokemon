package com.jbrown.pokemon.core.domain;

import com.jbrown.pokemon.core.enums.Type;

public class Species {
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
