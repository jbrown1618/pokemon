import com.jbrown.pokemon.entities.Pokemon;
import com.jbrown.pokemon.enums.Type;

public abstract class MoveExecutor {
    private Type type;

    public abstract void execute(Pokemon attacker, Pokemon target);

    public Type getType() {
        return type;
    }
}
