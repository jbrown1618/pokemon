package com.jbrown.pokemon.battle;

import com.jbrown.pokemon.core.domain.Pokemon;
import com.jbrown.pokemon.core.domain.Stats;
import com.jbrown.pokemon.core.domain.Move;
import com.jbrown.pokemon.core.enums.Type;

public class DamagingMove extends Move {
    private static double MAX_RANDOM_MULTIPLIER = 1.0;
    private static double MIN_RANDOM_MULTIPLIER = 0.85;
    private static double CRITICAL_HIT_PROBABILITY = 0.0625;

    private int basePower;
    private TypeEvaluator typeEvaluator = new TypeEvaluator();

    public DamagingMove(String name, Type type, int accuracy, int basePower) {
        super(name, accuracy, type);
        this.basePower = basePower;
    }

    public void execute(Pokemon attacker, Pokemon target) {
        Stats currentStats = target.getCurrentStats();
        currentStats.setHp(currentStats.getHp() - calculateDamage(attacker, target));
    }

    private int calculateDamage(Pokemon attacker, Pokemon target) {
        double damage = calculateBaseDamage(attacker, target) * calculateDamageModifier(attacker, target);
        return (int) Math.round(damage);
    }

    private double calculateBaseDamage(Pokemon attacker, Pokemon target) {
        return calculateLevelMultiplier(attacker)
            * calculateAttackDefenseRatio(attacker, target)
            * (double) basePower
            + 2;
    }

    private double calculateDamageModifier(Pokemon attacker, Pokemon target) {
        return calculateSameTypeAttackBonus(attacker)
            * typeEvaluator.getMultiplier(getType(), target.getSpecies().getType1())
            * typeEvaluator.getMultiplier(getType(), target.getSpecies().getType2())
            * calculateCriticalHitMultiplier()
            * calculateRandomMultiplier();
    }

    private double calculateLevelMultiplier(Pokemon attacker) {
        return (2 * (double) attacker.getLevel() + 10) / 250;
    }

    private double calculateAttackDefenseRatio(Pokemon attacker, Pokemon target) {
        return (double) attacker.getCurrentStats().getAttack() / (double) target.getCurrentStats().getDefense();
    }

    private double calculateSameTypeAttackBonus(Pokemon attacker) {
        return (hasSameTypeAttackBonus(attacker)) ? 1.5 : 1.0;
    }

    private boolean hasSameTypeAttackBonus(Pokemon attacker) {
        return getType().equals(attacker.getSpecies().getType1()) ||
            getType().equals(attacker.getSpecies().getType2());
    }

    private double calculateCriticalHitMultiplier() {
        return (Math.random() > 1 - getCriticalHitProbability()) ? 1.5 : 1.0;
    }

    private double getCriticalHitProbability() {
        return CRITICAL_HIT_PROBABILITY;
    }

    private double calculateRandomMultiplier() {
        return MIN_RANDOM_MULTIPLIER + (MAX_RANDOM_MULTIPLIER - MIN_RANDOM_MULTIPLIER) * Math.random();
    }
}
