"""Fetches data on all of the relevant Pokemon from the Pokemon API.

The API, located at https://pokeapi.co/, documents all of the data from various
pokemon versions. By fetching this data, we can then generate Java resources
for each species of Pokemon and its properties.
"""

from pprint import pformat
from collections import defaultdict, namedtuple
import math
import csv
# TODO: Rewrite using better async networking. import asyncio/aiohttp
import grequests

DEBUG = False

def debug(*argv):
  if DEBUG:
    print(*argv)


BASE_URL = 'https://pokeapi.co/api/v2'

Stats = namedtuple(
    'Stats',
    ['hp', 'attack', 'defense', 'special_attack', 'special_defense', 'speed'])

def pokeapi_parallel_list(url, results_per_request, max_results):
  """
  Takes a 'list' request URL (e.g. http://foo/entity), and shards it
  into multiple URLs to parallelize listing things (e.g.
  [http://foo/entity?limit=20, http://foo/entity?limit=20&offset=20, ...]).

  Returns a list of JSON responses.
  """
  reqs = []
  for shard in range(math.ceil(max_results / results_per_request)):
    offset = results_per_request * shard
    limit = min(results_per_request, max_results - offset)
    reqs.append(grequests.get(url + '?limit=%d&offset=%d' % (limit, offset)))

  responses = grequests.map(reqs)
  failed = list(filter(lambda resp: resp.status_code != 200, responses))
  if failed:
    raise Exception('Some requests failed: ' + str(failed))
  return map(lambda resp: resp.json(), responses)

def unicode_encoder(data):
  for line in data:
    yield line.encode('utf-8')

def list_pokemon(version='Silver', num=811):
  """TODO(arensonjr): An attempt to read from CSV rather than from API"""
  DATA_DIR = 'main/data/pokedex/pokedex/data/csv'

  # TODO: Turn these all into lazy / cached loaders.

  # TODO: Localize?
  english_id = None
  with open('%s/languages.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      if row['iso639'] == 'en' and row['iso3166'] == 'us':
        english_id = row['id']
        break

  version_id = None
  with open('%s/version_names.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      if row['local_language_id'] == english_id and row['name'] == version.title():
        version_id = row['version_id']
  version_group_id = None
  with open('%s/version_groups.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      if version.lower() in row['identifier'].split('-'):
        version_group_id = row['id']

  stat_names = {
      1: 'hp',
      2: 'attack',
      3: 'defense',
      4: 'special_attack',
      5: 'special_defense',
      6: 'speed',
      7: 'accuracy',
      8: 'evasion'
  }
  pokemon_stats_by_id = defaultdict(dict)
  pokemon_evs_by_id = defaultdict(dict)
  with open('%s/pokemon_stats.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      stat = stat_names[int(row['stat_id'])]
      pokemon_stats_by_id[row['pokemon_id']][stat] = int(row['base_stat'])
      pokemon_evs_by_id[row['pokemon_id']][stat] = int(row['effort'])
  pokemon_stats_by_id = { pokemon_id: Stats(**stats) for (pokemon_id, stats) in pokemon_stats_by_id.items() }
  pokemon_evs_by_id = { pokemon_id: Stats(**stats) for (pokemon_id, stats) in pokemon_evs_by_id.items() }

  game_indices_by_id = {}
  with open('%s/pokemon_game_indices.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      if row['version_id'] == version_id:
        game_indices_by_id[row['pokemon_id']] = row['game_index']

  moves_by_id = {}
  with open('%s/moves.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      # TODO: Class-ify?
      moves_by_id[row['id']] = Move(
          name=row['identifier'],
          power=row['power'],
          pp=row['pp'],
          accuracy=row['accuracy'],
          move_type='Normal', # TODO: types_by_id[row['type_id']]
          effect=None, # TODO: effects_by_id[row['effect_id']]
          effect_chance=row['effect_chance'])

  moves_by_pokemon_id = defaultdict(list)
  with open('%s/pokemon_moves.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      if row['version_group_id'] == version_group_id:
        moves_by_pokemon_id[row['pokemon_id']].append(moves_by_id[row['move_id']])

  all_pokemon = []
  with open('%s/pokemon.csv' % DATA_DIR, 'r', encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
      pokemon_id = row['id']
      # TODO: Object?
      if pokemon_id not in game_indices_by_id:
        # Wasn't in the requested version of the game -- skip it.
        continue

      all_pokemon.append(Pokemon(
          species=row['identifier'],
          base_stats=pokemon_stats_by_id[pokemon_id],
          evs=pokemon_evs_by_id[pokemon_id],
          game_index=game_indices_by_id[pokemon_id],
          moves=moves_by_pokemon_id[pokemon_id],
      ))
  return all_pokemon



def list_pokemon_old(version='silver', num=811):
  """TODO(arensonjr): Un-hardcode the current max number of pokemon."""

  # TODO: resolve version & version-group

  details_urls = [pokemon['url']
                  for response in pokeapi_parallel_list('%s/pokemon' % BASE_URL, 50, num)
                  for pokemon in response['results']]

  responses = grequests.map(grequests.get(url) for url in details_urls)
  failed = list(filter(lambda resp: resp.status_code != 200, responses))
  if failed:
    raise Exception('Some requests failed: ' + str(failed))

  return [Pokemon(resp.json(), version=version) for resp in responses]


  #return [Pokemon(p, version=version)
  #        for response in pokeapi_parallel_list('%s/pokemon' % BASE_URL, 50, num)
  #        for p in response['results']]


def find(ls, f):
  for x in ls:
    if f(x):
      return x
  raise Exception('No element in ' + str(ls) + ' satisfied ' + repr(f))

class Pokemon(object):
  def __init__(self, species, game_index, base_stats, evs, moves=[]):
    self.species = species
    self.game_index = game_index
    self.base_stats = base_stats
    self.evs = evs
    self.moves = moves

  # TODO(arensonjr): Pokemon's type(s)

  def enum_name(self):
    # Don't forget to replace '-'s for pokemon like "Ho-oh"
    return self.species.replace('-', '_').upper()

  def pretty_name(self):
    return self.species.title()

  def move_enum_list(self):
    return ['Move.' + move.enum_name() for move in self.moves]

  def __repr__(self):
    return 'Pokemon(\n  species=%s,\n  game_index=%s,\n  base_stats=%s,\n  evs=%s,\n  moves=%s\n)' % (
        self.species,self.game_index,self.base_stats,self.evs,pformat(self.moves, indent=4))

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
