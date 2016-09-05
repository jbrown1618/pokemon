"""Fetches data on all of the relevant Pokemon from the Pokemon API.

The API, located at https://pokeapi.co/, documents all of the data from various
pokemon versions. By fetching this data, we can then generate Java resources
for each species of Pokemon and its properties.
"""

from collections import defaultdict, namedtuple
import csv
import functools

DATA_DIR = 'main/data/pokedex/pokedex/data/csv'
ENGLISH_ID = '9'
STAT_NAMES = {
  '1': 'hp',
  '2': 'attack',
  '3': 'defense',
  '4': 'special_attack',
  '5': 'special_defense',
  '6': 'speed',
  '7': 'accuracy',
  '8': 'evasion',
}

# A lightweight data type for a Pokemon's stats.
Stats = namedtuple(
    'Stats',
    ['hp', 'attack', 'defense', 'special_attack', 'special_defense', 'speed'])


def memoize(func):
  '''
  Turns any function into a memoized function.

  Use as an annotation, e.g.:

      @memoize
      def myfunc(some, args):
          # expensive computation here
          return result
  '''
  cache = {}
  @functools.wraps(func)
  def memoizer(*args, **kwargs):
    key = str(args) + str(kwargs)
    if key not in cache:
        cache[key] = func(*args, **kwargs)
    return cache[key]
  return memoizer

def read_csv(filename):
  '''
  Reads the contents of the CSV file at '%(DATA_DIR)s/%(filename)s.csv'.

  Yields one row at a time, as a dictionary keyed by column name.
  '''
  with open('%s/%s.csv' % (DATA_DIR, filename), 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      yield row

@memoize
def version_id(version):
  '''
  Returns the version ID (example version: 'silver') for a specified game
  version.
  '''
  for row in read_csv('versions'):
    if row['identifier'] == version.lower():
      return row['id']

@memoize
def generation(version):
  for row in read_csv('version_groups'):
    if row['id'] == version_group_id(version):
      return row['generation_id']


@memoize
def version_group_id(version):
  '''
  Returns the version group ID (example version group: 'gold-silver') for a
  specified game version.
  '''
  for row in read_csv('version_groups'):
    if version.lower() in row['identifier'].split('-'):
      return row['id']

@memoize
def game_indices(version):
  '''
  Returns a map from Pokemon ID to Pokedex index from a particular version.
  '''
  game_indices_by_id = {}
  for row in read_csv('pokemon_game_indices'):
    if row['version_id'] == version_id(version):
      game_indices_by_id[row['pokemon_id']] = row['game_index']
  return game_indices_by_id

@memoize
def moves():
  '''Returns each move in the game, keyed by ID.'''
  moves_by_id = {}
  for row in read_csv('moves'):
    moves_by_id[row['id']] = Move(
        name=row['identifier'],
        power=row['power'],
        pp=row['pp'],
        accuracy=row['accuracy'],
        move_type='Normal', # TODO: types_by_id[row['type_id']]
        effect=None, # TODO: effects_by_id[row['effect_id']]
        effect_chance=row['effect_chance'])
  return moves_by_id

@memoize
def moves_per_pokemon(version):
  '''Returns a list of each Pokemon's moves, keyed by Pokemon ID.'''
  moves_by_pokemon_id = defaultdict(list)
  for row in read_csv('pokemon_moves'):
    if row['version_group_id'] == version_group_id(version):
      moves_by_pokemon_id[row['pokemon_id']].append(moves()[row['move_id']])
  return moves_by_pokemon_id

@memoize
def stats():
  '''Returns all pokemon's stats, keyed by Pokemon ID.'''
  pokemon_stats_by_id = defaultdict(dict)
  for row in read_csv('pokemon_stats'):
    stat = STAT_NAMES[row['stat_id']]
    pokemon_stats_by_id[row['pokemon_id']][stat] = int(row['base_stat'])
  return { pokemon_id: Stats(**stats) for (pokemon_id, stats) in pokemon_stats_by_id.items() }

@memoize
def evs():
  '''Returns the EVs obtained by fighting Pokemon, keyed by Pokemon ID.'''
  pokemon_evs_by_id = defaultdict(dict)
  for row in read_csv('pokemon_stats'):
    stat = STAT_NAMES[row['stat_id']]
    pokemon_evs_by_id[row['pokemon_id']][stat] = int(row['effort'])
  return { pokemon_id: Stats(**stats) for (pokemon_id, stats) in pokemon_evs_by_id.items() }

@memoize
def type_names(version):
  '''Returns type names keyed by type ID.'''
  # TODO(arensonjr): Support 'damage class', for Gen IV and up
  return {row['id']: row['identifier']
          for row in read_csv('types')
          if row['generation_id'] <= generation(version)}

@memoize
def types(version):
  '''Returns each Pokemon's type(s) by Pokemon ID.'''
  types = defaultdict(lambda: defaultdict(str))
  for row in read_csv('pokemon_types'):
    # TODO(arensonjr): Fairy didn't exist before Gen VI, but
    # the database we're given doesn't split out Pokemon types
    # per-version, so we have to work around it manually.
    type_name = type_names(version).get(row['type_id'], 'normal')

    types[row['pokemon_id']][row['slot']] = type_name

  return types

@memoize
def list_pokemon(version='Silver'):
  '''Lists all Pokemon from a particular game version.'''
  # TODO(arensonjr): optionally localize, to support non-en-US versions?
  all_pokemon = []
  for row in read_csv('pokemon'):
    pokemon_id = row['id']

    # If the pokemon doesn't exist in this version of the game, don't include
    # it in the returned list.
    if pokemon_id not in game_indices(version):
      continue

    all_pokemon.append(Pokemon(
        species=row['identifier'],
        base_stats=stats()[pokemon_id],
        evs=evs()[pokemon_id],
        game_index=game_indices(version)[pokemon_id],
        moves=moves_per_pokemon(version)[pokemon_id],
        type1=types(version)[pokemon_id]['1'],
        type2=types(version)[pokemon_id]['2']))

  return all_pokemon


class Pokemon(object):
  def __init__(self, species, game_index, base_stats, evs, type1, type2, moves=[]):
    self.species = species
    self.game_index = game_index
    self.base_stats = base_stats
    self.evs = evs
    self.moves = moves
    self.type1 = type1
    self.type2 = type2 or None

  def enum_name(self):
    # Don't forget to replace '-'s for pokemon like "Ho-oh"
    return self.species.replace('-', '_').upper()

  def pretty_name(self):
    return self.species.title()

  def move_enum_list(self):
    return ['Move.' + move.enum_name() for move in self.moves]

  def type1_enum(self):
    return 'Type.' + self.type1.upper()

  def type2_enum(self):
    return 'Type.' + self.type2.upper() if self.type2 else 'null'

  def __repr__(self):
    return ('Pokemon(\n  '
            'species=%s,\n  '
            'game_index=%s,\n  '
            'base_stats=%s,\n  '
            'evs=%s,\n  '
            'type1=%s,\n  '
            'type2=%s\n  '
            'moves=%s\n)') % (
        self.species, self.game_index, self.base_stats, self.evs, self.type1, self.type2, self.moves)

class Move(object):
  def __init__(self, name, pp, move_type, power=None, effect=None, effect_chance=None, accuracy=None):
    self.name = name
    self.pp = pp
    self.move_type = move_type
    self.power = power or None
    self.effect = effect or None
    self.effect_chance = effect_chance or None
    self.accuracy = accuracy or None

  def pretty_name(self):
    return self.name.replace('-', ' ').title()

  def enum_name(self):
    return self.name.replace('-', '_').upper()

  def name(self):
    return self.name

  def type_enum(self):
    return self.move_type.upper()

  def __repr__(self):
    return 'Move(name=%s,pp=%s,move_type=%s,power=%s,effect=%s,effect_chance=%s,accuracy=%s)' % (
        self.name, self.pp, self.move_type, self.power, self.effect, self.effect_chance, self.accuracy)

  def __eq__(self, other):
    return repr(self) == repr(other)

  def __hash__(self):
    return hash(repr(self))
