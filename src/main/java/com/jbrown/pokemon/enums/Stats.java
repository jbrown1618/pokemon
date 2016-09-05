package com.jbrown.pokemon.enums;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Stats {
    public abstract int getHp();
    public abstract int getAttack();
    public abstract int getDefense();
    public abstract int getSpecialAttack();
    public abstract int getSpecialDefense();
    public abstract int getSpeed();

    public static Stats create(int hp, int attack, int defense, int spAtk, int spDef, int speed) {
      return new AutoValue_Stats(hp, attack, defense, spAtk, spDef, speed);
    }

    /**
     * Returns a {@link Stats} with modified HP.
     */
    public Stats withHp(int newHp) {
      return create(newHp, getAttack(), getDefense(), getSpecialAttack(), getSpecialDefense(), getSpeed());
    }
}
