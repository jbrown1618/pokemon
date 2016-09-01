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

    private Map<Type, TypeProperties> effectivenessMap = initEffectivenessMap();

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

    private Map<Type, TypeProperties> initEffectivenessMap() {
        Map<Type, TypeProperties> map = new HashMap<>();
        
        map.put(Type.NORMAL, new TypeProperties()
            .addImmunity(Type.GHOST)
            .addWeakness(Type.FIGHTING));

        map.put(Type.FIRE, new TypeProperties()
            .addWeakness(Type.WATER)
            .addWeakness(Type.GROUND)
            .addWeakness(Type.ROCK)
            .addResistance(Type.FIRE)
            .addResistance(Type.GRASS)
            .addResistance(Type.ICE)
            .addResistance(Type.BUG)
            .addResistance(Type.STEEL));

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
