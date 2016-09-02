package com.jbrown.pokemon.battle;

import com.jbrown.pokemon.entities.Pokemon;
import com.jbrown.pokemon.entities.Stats;
import com.jbrown.pokemon.enums.Species;
import com.jbrown.pokemon.enums.Type;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class DamagingMoveExecutorTest {
    @Test
    public void testCausesDamage() {
        DamagingMoveExecutor executor = new DamagingMoveExecutor(Type.NORMAL, 1.0, 100);
        Pokemon attacker = new Pokemon(Species.BULBASAUR, 100, new Stats(100, 100, 100, 100, 100, 100));
        Pokemon target = new Pokemon(Species.BULBASAUR, 100, new Stats(100, 100, 100, 100, 100, 100));

        int initialHp = target.getCurrentStats().getHp();
        executor.execute(attacker, target);
        int finalHp = target.getCurrentStats().getHp();

        assertNotEquals(initialHp, finalHp);
    }
}
