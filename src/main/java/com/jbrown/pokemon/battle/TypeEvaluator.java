package com.jbrown.pokemon.battle;

import com.jbrown.pokemon.enums.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeEvaluator {
    private static final double INEFFECTIVE = 0.0;
    private static final double NOT_VERY_EFFECTIVE = 0.5;
    private static final double EFFECTIVE = 1.0;
    private static final double SUPER_EFFECTIVE = 2.0;

    private Map<Type, TypeProperties> effectivenessMap;

    public TypeEvaluator() {
        initEffectivenessMap();
    }

    public double getMultiplier(Type attackingType, Type defendingType) {
        TypeProperties typeProperties = effectivenessMap.get(defendingType);
        if (typeProperties.hasImmunity(attackingType)) {
            return INEFFECTIVE;
        } else if (typeProperties.hasResistance(attackingType)) {
            return NOT_VERY_EFFECTIVE;
        } else if (typeProperties.hasWeakness(attackingType)) {
            return SUPER_EFFECTIVE;
        } else {
            return EFFECTIVE;
        }
    }

    private void initEffectivenessMap() {
        effectivenessMap.put(Type.NORMAL, new TypeProperties()
                .addImmunity(Type.GHOST)
                .addWeakness(Type.FIGHTING));

        effectivenessMap.put(Type.FIRE, new TypeProperties()
                .addWeakness(Type.WATER)
                .addWeakness(Type.GROUND)
                .addWeakness(Type.ROCK)
                .addResistance(Type.FIRE)
                .addResistance(Type.GRASS)
                .addResistance(Type.ICE)
                .addResistance(Type.BUG)
                .addResistance(Type.STEEL));

        effectivenessMap.put(Type.WATER, new TypeProperties()
                .addWeakness(Type.ELECTRIC)
                .addWeakness(Type.GRASS)
                .addResistance(Type.FIRE)
                .addResistance(Type.WATER)
                .addResistance(Type.ICE)
                .addResistance(Type.STEEL));

        effectivenessMap.put(Type.ELECTRIC, new TypeProperties()
                .addWeakness(Type.GROUND)
                .addResistance(Type.ELECTRIC)
                .addResistance(Type.FLYING)
                .addResistance(Type.STEEL));

        effectivenessMap.put(Type.GRASS, new TypeProperties()
                .addWeakness(Type.FIRE)
                .addWeakness(Type.ICE)
                .addWeakness(Type.POISON)
                .addWeakness(Type.FLYING)
                .addWeakness(Type.BUG)
                .addResistance(Type.WATER)
                .addResistance(Type.ELECTRIC)
                .addResistance(Type.GRASS)
                .addResistance(Type.GROUND));

        effectivenessMap.put(Type.ICE, new TypeProperties()
                .addWeakness(Type.FIRE)
                .addWeakness(Type.FIGHTING)
                .addWeakness(Type.ROCK)
                .addWeakness(Type.STEEL)
                .addResistance(Type.ICE));

        effectivenessMap.put(Type.FIGHTING, new TypeProperties()
                .addWeakness(Type.FLYING)
                .addWeakness(Type.PSYCHIC)
                .addResistance(Type.BUG)
                .addResistance(Type.ROCK)
                .addResistance(Type.DARK));

        effectivenessMap.put(Type.POISON, new TypeProperties()
                .addWeakness(Type.GROUND)
                .addWeakness(Type.PSYCHIC)
                .addResistance(Type.GRASS)
                .addResistance(Type.FIGHTING)
                .addResistance(Type.POISON)
                .addResistance(Type.BUG));

        effectivenessMap.put(Type.GROUND, new TypeProperties()
                .addImmunity(Type.ELECTRIC)
                .addWeakness(Type.WATER)
                .addWeakness(Type.GRASS)
                .addWeakness(Type.ICE)
                .addResistance(Type.POISON)
                .addResistance(Type.ROCK));

        effectivenessMap.put(Type.FLYING, new TypeProperties()
                .addImmunity(Type.GROUND)
                .addWeakness(Type.ELECTRIC)
                .addWeakness(Type.ICE)
                .addWeakness(Type.ROCK)
                .addResistance(Type.GRASS)
                .addResistance(Type.FIGHTING)
                .addResistance(Type.BUG));

        effectivenessMap.put(Type.PSYCHIC, new TypeProperties()
                .addWeakness(Type.BUG)
                .addWeakness(Type.GHOST)
                .addWeakness(Type.DARK)
                .addResistance(Type.FIGHTING)
                .addResistance(Type.PSYCHIC));

        effectivenessMap.put(Type.BUG, new TypeProperties()
                .addWeakness(Type.FIRE)
                .addWeakness(Type.FLYING)
                .addWeakness(Type.ROCK)
                .addResistance(Type.GRASS)
                .addResistance(Type.FIGHTING)
                .addResistance(Type.GROUND));

        effectivenessMap.put(Type.ROCK, new TypeProperties()
                .addWeakness(Type.WATER)
                .addWeakness(Type.GRASS)
                .addWeakness(Type.FIGHTING)
                .addWeakness(Type.STEEL)
                .addResistance(Type.NORMAL)
                .addResistance(Type.FIRE)
                .addResistance(Type.POISON)
                .addResistance(Type.FLYING));

        effectivenessMap.put(Type.GHOST, new TypeProperties()
                .addImmunity(Type.NORMAL)
                .addImmunity(Type.FIGHTING)
                .addWeakness(Type.GHOST)
                .addWeakness(Type.DARK)
                .addResistance(Type.POISON)
                .addResistance(Type.BUG));

        effectivenessMap.put(Type.DRAGON, new TypeProperties()
                .addWeakness(Type.ICE)
                .addWeakness(Type.DRAGON)
                .addResistance(Type.FIRE)
                .addResistance(Type.WATER)
                .addResistance(Type.ELECTRIC)
                .addResistance(Type.GRASS));

        effectivenessMap.put(Type.DARK, new TypeProperties()
                .addImmunity(Type.PSYCHIC)
                .addWeakness(Type.FIGHTING)
                .addWeakness(Type.BUG)
                .addResistance(Type.GHOST)
                .addResistance(Type.DARK));

        effectivenessMap.put(Type.STEEL, new TypeProperties()
                .addImmunity(Type.POISON)
                .addWeakness(Type.FIRE)
                .addWeakness(Type.FIGHTING)
                .addWeakness(Type.GROUND)
                .addResistance(Type.NORMAL)
                .addResistance(Type.GRASS)
                .addResistance(Type.ICE)
                .addResistance(Type.FLYING)
                .addResistance(Type.PSYCHIC)
                .addResistance(Type.BUG)
                .addResistance(Type.ROCK)
                .addResistance(Type.DRAGON)
                .addResistance(Type.STEEL));
    }

    private class TypeProperties {
        private Set<Type> immunities = new HashSet<>();
        private Set<Type> resistances = new HashSet<>();
        private Set<Type> weaknesses = new HashSet<>();

        public TypeProperties addImmunity(Type immunity) {
            this.immunities.add(immunity);
            return this;
        }

        public TypeProperties addResistance(Type resistance) {
            this.resistances.add(resistance);
            return this;
        }

        public TypeProperties addWeakness(Type weakness) {
            this.weaknesses.add(weakness);
            return this;
        }

        public boolean hasImmunity(Type type) {
            return immunities.contains(type);
        }

        public boolean hasResistance(Type type) {
            return resistances.contains(type);
        }

        public boolean hasWeakness(Type type) {
            return weaknesses.contains(type);
        }
    }
}
