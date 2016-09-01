package com.jbrown.pokemon.enums;

import com.jbrown.pokemon.battle.DamagingMoveExecutor;
import com.jbrown.pokemon.battle.MoveExecutor;
import com.jbrown.pokemon.entities.Pokemon;

public enum Move {
    TACKLE("Tackle", new DamagingMoveExecutor(Type.NORMAL, 1.0, 35));

    private String name;
    private MoveExecutor executor;

    private Move(String name, MoveExecutor executor) {
        this.name = name;
        this.executor = executor;
    }

    public void execute(Pokemon attacker, Pokemon target) {
        this.executor.execute(attacker, target);
    }
}
