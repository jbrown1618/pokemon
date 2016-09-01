package com.jbrown.pokemon.battle;

import com.jbrown.pokemon.entities.Pokemon;
import com.jbrown.pokemon.enums.Type;

import java.util.function.BiConsumer;

public abstract class MoveExecutor {
    protected Type type;
    protected double accuracy;

    public abstract void execute(Pokemon attacker, Pokemon target);

    public Type getType() {
        return type;
    }
}
