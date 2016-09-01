package com.jbrown.pokemon.battle;

import com.jbrown.pokemon.entities.Pokemon;
import com.jbrown.pokemon.entities.Stats;
import com.jbrown.pokemon.enums.Type;

public class DamagingMoveExecutor extends MoveExecutor {
    private int basePower;
    private TypeEvaluator typeEvaluator = new TypeEvaluator();

    public DamagingMoveExecutor(Type type, double accuracy, int basePower) {
        this.type = type;
        this.accuracy = accuracy;
        this.basePower = basePower;
    }

    public void execute (Pokemon attacker, Pokemon target) {
        Stats currentStats = target.getCurrentStats();
        currentStats.setHp(currentStats.getHp() - calculateDamage(attacker, target));
    }

    public int calculateDamage(Pokemon attacker, Pokemon target) {
        double levelPart = (2 * (double)attacker.getLevel() + 10) / 250;
        double statsPart = (double)attacker.getCurrentStats().getAttack() / (double)target.getCurrentStats().getDefense();
        double movePart = (double)basePower;
        double damage = (levelPart * statsPart * movePart * 2) * calculateDamageModifier(attacker, target);
        return (int)Math.round(damage);
    }

    private double calculateDamageModifier(Pokemon attacker, Pokemon target) {
        double sameTypeAttackBonus = (type.equals(attacker.getSpecies().getType1()) || type.equals(attacker.getSpecies().getType2())) ? 1.5 : 1.0;
        double firstTypeMultiplier = typeEvaluator.getMultiplier(type, target.getSpecies().getType1());
        double secondTypeMultiplier = typeEvaluator.getMultiplier(type, target.getSpecies().getType2());
        double criticalMultiplier = 1.0;
        double randomMultiplier = 0.85 + 0.25 * Math.random();
        return sameTypeAttackBonus * firstTypeMultiplier * secondTypeMultiplier * criticalMultiplier * randomMultiplier;
    }
}
