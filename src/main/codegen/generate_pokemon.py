"""Generates a bunch of Java code for each Species.

Run with:
    bazel run //codegen:generate_pokemon -- --version=silver
"""

import argparse

from main.codegen.fetch_pokemon import list_pokemon

# Enable to generate status output for this script.
DEBUG = False
def debug(output):
    if DEBUG:
        print(output)

# Template for the 'Species.java' file.
#
# Variables that must be substituted in:
#   * 'version':
#     The version of Pokemon that are being generated (e.g. 'silver')
#   * 'species_initializers':
#     The initializers, for instantiations of each individual species of
#     pokemon.
JAVA_POKEMON_TEMPLATE = '''package com.jbrown.pokemon.entities.%(version)s;

import static java.util.Optional.ofNullable;
import static com.jbrown.pokemon.enums.Type.*;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.jbrown.pokemon.enums.Stats;
import com.jbrown.pokemon.enums.Type;
import java.util.List;
import java.util.Optional;

@AutoValue
public abstract class Species {

  public abstract String getSpecies();
  public abstract Stats getBaseStats();
  public abstract Type getType1();
  public abstract Optional<Type> getType2();
  public abstract ImmutableList<Move> getMoves();

  /**
   * Initializes a new Pokemon species.
   */
  private static Species create(
      String species, Stats baseStats, Type type1, Optional<Type> type2, Move... moves) {
    return new AutoValue_Species(
        species, baseStats, type1, type2, ImmutableList.copyOf(moves));
  }

  static ImmutableSet<Species> values = null;
  public static ImmutableSet<Species> values() {
    if (values == null) {
      // Sadly, if we just create this in-line, it exceeds the 65535 byte compilation limit.
      values = ImmutableSet.<Species>builder()%(species_initializers_adds)s
          .build();

    }
    return values;
  }

  %(species_initializers)s
}
'''
# Groups a bunch of pokemon together for static initialization.
JAVA_POKEMON_GROUP_TEMPLATE = '''
  // Split into pieces because of initialization limits...
  private static ImmutableSet<Species> values%(n)d() {
    return ImmutableSet.<Species>builder()%(species)s
        .build();
  }
'''
# Template for the nth static initializer method, since we sadly can't do it all in-line.
JAVA_POKEMON_GROUP_INITIALIZER_FN = '''
          .addAll(values%d())'''

# Template for the enum instantiation of a single species of pokemon.
#
# Variables that must be substituted in:
#   * 'species_common': The common name of the species (e.g. 'Bulbasaur').
#   * 'hp': Base HP stat.
#   * 'atk': Base attack stat.
#   * 'def': Base defense stat.
#   * 'spatk': Base special attack stat.
#   * 'spdef': Base special defense stat.
#   * 'spd': Base speed stat.
#   * 'type1': Pokemon's primary type.
#   * 'type2': Pokemon's secondary type, or 'null'.
#   * 'move_enum_list': A list of all of the pokemon's moves, used as enum values -- so, UPPER_CASE.
JAVA_SINGLE_POKEMON_TEMPLATE = '''
          .add(create(
              "%(species_common)s",
              Stats.create(%(hp)s, %(atk)s, %(def)s, %(spatk)s, %(spdef)s, %(spd)s),
              %(type1)s,
              ofNullable(%(type2)s),
              %(move_enum_list)s))'''

# Template for the 'Move.java' file.
#
# Variables that must be substituted in:
#   * 'version':
#     The version of Pokemon that are being generated (e.g. 'silver')
#   * 'all_moves':
#     The enum instantiations of each individual move, joined by commas.
JAVA_MOVE_TEMPLATE = '''package com.jbrown.pokemon.entities.%(version)s;

import static java.util.Optional.ofNullable;

import com.jbrown.pokemon.enums.Type;
import java.util.Optional;

public enum Move {
  %(all_moves)s;

  private final String moveName;
  private final int pp;
  private final Type type;
  private final Optional<Integer> power;
  private final Optional<String> effect; // TODO(arensonjr): Should be an enum
  private final Optional<Integer> effectChance; // Out of 100
  private final Optional<Integer> accuracy; // Out of 100

  /**
   * Initializes a new Pokemon move / attack.
   */
  private Move(
      String moveName,
      int pp,
      Type type,
      Optional<Integer> power,
      Optional<String> effect,
      Optional<Integer> effectChance,
      Optional<Integer> accuracy) {
    this.moveName = moveName;
    this.pp = pp;
    this.type = type;
    this.power = power;
    this.effect = effect;
    this.effectChance = effectChance;
    this.accuracy = accuracy;
  }
}
'''

# Template for the enum instantiation of a single Pokemon move.
#
# Variables that must be substituted in:
#   * 'move_upper': The ENUM_CASE name for the move (e.g. 'LEECH_SEED').
#   * 'move_common': The common name for the move (e.g. 'Leech Seed').
#   * 'pp': The PP of the move (int).
#   * 'move_type': The type of the move (e.g. NORMAL).
#   * 'power': The power of the move (int), or if it's powerless, null.
#   * 'effect': The effect the move causes, if any (e.g. 'burn'), or 'null'
#   * 'effect_chance': The chance (out of 100) that the effect will trigger (e.g. 100), or 'null'
#   * 'accuracy': The move's accuracy (if any), or 'null'
JAVA_SINGLE_MOVE_TEMPLATE = '''
  %(move_upper)s(
      "%(move_common)s",
      %(pp)s,
      Type.%(move_type)s,
      ofNullable(%(power)s),
      ofNullable("%(effect)s"),
      ofNullable(%(effect_chance)s),
      ofNullable(%(accuracy)s))'''


parser = argparse.ArgumentParser(
        description='Autogenerate enums for the Pokemon battle engine.')
parser.add_argument(
        '--version',
        help='Version of the game to generate docs for (e.g. "silver")')
parser.add_argument(
        '--java_base_dir',
        help='Root directory for Java sources (contains "com/").')

def main():
    args = parser.parse_args()
    debug('>>> Args: %s' % args)

    debug('>>> Listing all pokemon...')
    all_pokemon = list_pokemon(version=args.version)
    debug('>>> Listed %d pokemon.' % len(all_pokemon))

    target_dir = '%s/com/jbrown/pokemon/entities/%s' % (
            args.java_base_dir, args.version.lower())

    debug('>>> Generating Move.java...')
    all_moves = set(move for pokemon in all_pokemon for move in pokemon.moves)
    move_enum_cases = []
    for move in all_moves:
        details = {
            'move_upper': move.enum_name(),
            'move_common': move.pretty_name(),
            'pp': move.pp,
            'move_type': move.type_enum(),
            'power': move.power or 'null',
            'effect': move.effect or 'null',
            'effect_chance': move.effect_chance or 'null',
            'accuracy': move.accuracy or 'null',
        }
        move_enum_cases.append(JAVA_SINGLE_MOVE_TEMPLATE % details)
    file_details = {
            'version': args.version,
            'all_moves': ','.join(move_enum_cases)
    }
    with open('%s/Move.java' % target_dir, 'w+') as f:
        f.write(JAVA_MOVE_TEMPLATE % file_details)

    debug('>>> Generating Species.java...')
    pokemon_enum_cases = []
    for pokemon in all_pokemon:
        details = {
                'species_common': pokemon.pretty_name(),
                'hp': pokemon.base_stats.hp,
                'atk': pokemon.base_stats.attack,
                'def': pokemon.base_stats.defense,
                'spatk': pokemon.base_stats.special_attack,
                'spdef': pokemon.base_stats.special_defense,
                'spd': pokemon.base_stats.speed,
                'type1': pokemon.type1_enum(),
                'type2': pokemon.type2_enum(),
                'move_enum_list': ','.join(pokemon.move_enum_list())
        }
        pokemon_enum_cases.append(JAVA_SINGLE_POKEMON_TEMPLATE % details)

    pokemon_enum_cases_pieces = [
            JAVA_POKEMON_GROUP_TEMPLATE % {'n': i, 'species': ''.join(piece)}
            for (i, piece) in enumerate(chunks(pokemon_enum_cases))]

    file_details = {
            'version': args.version.lower(),
            'species_initializers_adds': '\n'.join([
                JAVA_POKEMON_GROUP_INITIALIZER_FN % i
                for i in range(len(pokemon_enum_cases_pieces))]),
            'species_initializers': ''.join(pokemon_enum_cases_pieces),
    }
    with open('%s/Species.java' % target_dir, 'w+') as f:
        f.write(JAVA_POKEMON_TEMPLATE % file_details)

    debug('>>> Done!')

def chunks(ls, n=10):
    """Yields n-sized chunks of the list."""
    for i in range(0, len(ls), n):
        yield ls[i : i + n]


if __name__ == "__main__":
    main()
