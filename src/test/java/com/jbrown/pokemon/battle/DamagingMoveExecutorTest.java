package com.jbrown.pokemon.battle;

import com.google.common.collect.Iterables;
import com.jbrown.pokemon.entities.Pokemon;
import com.jbrown.pokemon.entities.silver.Species;
import com.jbrown.pokemon.enums.Stats;
import com.jbrown.pokemon.enums.Type;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class DamagingMoveExecutorTest {
    @Test
    public void testCausesDamage() {
        Species species = Iterables.getFirst(Species.values(), null);
        DamagingMoveExecutor executor = new DamagingMoveExecutor(Type.NORMAL, 1.0, 100);
        Pokemon attacker = new Pokemon(species, 100, Stats.create(100, 100, 100, 100, 100, 100));
        Pokemon target = new Pokemon(species, 100, Stats.create(100, 100, 100, 100, 100, 100));

        int initialHp = target.getCurrentStats().getHp();
        executor.execute(attacker, target);
        int finalHp = target.getCurrentStats().getHp();

        assertNotEquals(initialHp, finalHp);
    }
}
