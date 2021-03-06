package com.jbrown.pokemon.core.domain;

import com.jbrown.pokemon.core.enums.Type;

public abstract class Move {
    private String name;
    private int accuracy;
    private Type type;

    public Move(String name, int accuracy, Type type) {
        this.name = name;
        this.accuracy = accuracy;
        this.type = type;
    }

    public abstract void execute(Pokemon attacker, Pokemon target);

    public String getName() {
        return name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Type getType() {
        return type;
    }
}
