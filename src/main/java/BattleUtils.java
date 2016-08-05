
public class BattleUtils {

//    public static int calculateDamage(Pokemon attacker, Pokemon target, DamagingMoveExecutor move) {
//        double levelPart = (2 * (double)attacker.getLevel() + 10) / 250;
//        double statsPart = (double)attacker.getAttack() / (double)target.getDefense();
//        double movePart = (double)move.getBasePower();
//        double damage = (levelPart * statsPart * movePart * 2) * calculateDamageModifier(attacker, target, move);
//        return (int)Math.round(damage);
//    }
//
//    private static double calculateDamageModifier(Pokemon attacker, Pokemon target, DamagingMoveExecutor move) {
//        double stab = (move.getType() == attacker.getSpecies().getType1() || move.getType() == attacker.getSpecies().getType2()) ? 1.5 : 1.0;
//        double firstTypeMultiplier = calculateTypeMultiplier(move.getType(), target.getSpecies().getType1());
//        double secondTypeMultiplier = calculateTypeMultiplier(move.getType(), target.getSpecies().getType2());
//        double criticalMultiplier = 1.0;
//        double randomMultiplier = 0.85 + 0.25 * Math.random();
//        return stab * firstTypeMultiplier * secondTypeMultiplier * criticalMultiplier * randomMultiplier;
//    }
//
//    private static double calculateTypeMultiplier(Type attackingType, Type defendingType) {
//        return 1.0;
//    }
}
