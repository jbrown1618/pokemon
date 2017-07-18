import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PokedexComponent} from './pokedex/pokedex.component';
import {PartyComponent} from './party/party.component';
import {BattleComponent} from './battle/battle.component';

const routes: Routes = [ {
  path: 'pokedex',
  component: PokedexComponent
}, {
  path: 'party',
  component: PartyComponent
}, {
  path: 'battle',
  component: BattleComponent
}, {
  path: '',
  redirectTo: '/pokedex',
  pathMatch: 'full'
} ];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
