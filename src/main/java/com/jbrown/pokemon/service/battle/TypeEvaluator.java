package com.jbrown.pokemon.service.battle;

import com.jbrown.pokemon.model.enums.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.jbrown.pokemon.model.enums.Type.*;

public class TypeEvaluator {
    private static final double INEFFECTIVE = 0.0;
    private static final double NOT_VERY_EFFECTIVE = 0.5;
    private static final double EFFECTIVE = 1.0;
    private static final double SUPER_EFFECTIVE = 2.0;

    private Map<Type, TypeProperties> effectivenessMap = initEffectivenessMap();

    public double getMultiplier(Type attackingType, Type defendingType) {
        if (defendingType == null) {
            return EFFECTIVE;
        }

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

    private Map<Type, TypeProperties> initEffectivenessMap() {
        Map<Type, TypeProperties> map = new HashMap<>();
        map.put(NORMAL, new TypeProperties()
                .addImmunity(GHOST)
                .addWeakness(FIGHTING));

        map.put(FIRE, new TypeProperties()
                .addWeakness(WATER)
                .addWeakness(GROUND)
                .addWeakness(ROCK)
                .addResistance(FIRE)
                .addResistance(GRASS)
                .addResistance(ICE)
                .addResistance(BUG)
                .addResistance(STEEL));

        map.put(WATER, new TypeProperties()
                .addWeakness(ELECTRIC)
                .addWeakness(GRASS)
                .addResistance(FIRE)
                .addResistance(WATER)
                .addResistance(ICE)
                .addResistance(STEEL));

        map.put(ELECTRIC, new TypeProperties()
                .addWeakness(GROUND)
                .addResistance(ELECTRIC)
                .addResistance(FLYING)
                .addResistance(STEEL));

        map.put(GRASS, new TypeProperties()
                .addWeakness(FIRE)
                .addWeakness(ICE)
                .addWeakness(POISON)
                .addWeakness(FLYING)
                .addWeakness(BUG)
                .addResistance(WATER)
                .addResistance(ELECTRIC)
                .addResistance(GRASS)
                .addResistance(GROUND));

        map.put(ICE, new TypeProperties()
                .addWeakness(FIRE)
                .addWeakness(FIGHTING)
                .addWeakness(ROCK)
                .addWeakness(STEEL)
                .addResistance(ICE));

        map.put(FIGHTING, new TypeProperties()
                .addWeakness(FLYING)
                .addWeakness(PSYCHIC)
                .addResistance(BUG)
                .addResistance(ROCK)
                .addResistance(DARK));

        map.put(POISON, new TypeProperties()
                .addWeakness(GROUND)
                .addWeakness(PSYCHIC)
                .addResistance(GRASS)
                .addResistance(FIGHTING)
                .addResistance(POISON)
                .addResistance(BUG));

        map.put(GROUND, new TypeProperties()
                .addImmunity(ELECTRIC)
                .addWeakness(WATER)
                .addWeakness(GRASS)
                .addWeakness(ICE)
                .addResistance(POISON)
                .addResistance(ROCK));

        map.put(FLYING, new TypeProperties()
                .addImmunity(GROUND)
                .addWeakness(ELECTRIC)
                .addWeakness(ICE)
                .addWeakness(ROCK)
                .addResistance(GRASS)
                .addResistance(FIGHTING)
                .addResistance(BUG));

        map.put(PSYCHIC, new TypeProperties()
                .addWeakness(BUG)
                .addWeakness(GHOST)
                .addWeakness(DARK)
                .addResistance(FIGHTING)
                .addResistance(PSYCHIC));

        map.put(BUG, new TypeProperties()
                .addWeakness(FIRE)
                .addWeakness(FLYING)
                .addWeakness(ROCK)
                .addResistance(GRASS)
                .addResistance(FIGHTING)
                .addResistance(GROUND));

        map.put(ROCK, new TypeProperties()
                .addWeakness(WATER)
                .addWeakness(GRASS)
                .addWeakness(FIGHTING)
                .addWeakness(STEEL)
                .addResistance(NORMAL)
                .addResistance(FIRE)
                .addResistance(POISON)
                .addResistance(FLYING));

        map.put(GHOST, new TypeProperties()
                .addImmunity(NORMAL)
                .addImmunity(FIGHTING)
                .addWeakness(GHOST)
                .addWeakness(DARK)
                .addResistance(POISON)
                .addResistance(BUG));

        map.put(DRAGON, new TypeProperties()
                .addWeakness(ICE)
                .addWeakness(DRAGON)
                .addResistance(FIRE)
                .addResistance(WATER)
                .addResistance(ELECTRIC)
                .addResistance(GRASS));

        map.put(DARK, new TypeProperties()
                .addImmunity(PSYCHIC)
                .addWeakness(FIGHTING)
                .addWeakness(BUG)
                .addResistance(GHOST)
                .addResistance(DARK));

        map.put(STEEL, new TypeProperties()
                .addImmunity(POISON)
                .addWeakness(FIRE)
                .addWeakness(FIGHTING)
                .addWeakness(GROUND)
                .addResistance(NORMAL)
                .addResistance(GRASS)
                .addResistance(ICE)
                .addResistance(FLYING)
                .addResistance(PSYCHIC)
                .addResistance(BUG)
                .addResistance(ROCK)
                .addResistance(DRAGON)
                .addResistance(STEEL));

        return map;
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
