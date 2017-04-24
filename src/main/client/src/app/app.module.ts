import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {PokedexComponent} from './pokedex/pokedex.component';
import {PartyComponent} from './party/party.component';
import {BattleComponent} from './battle/battle.component';
import {routing} from './app.routing';
import { NavbarComponent } from './navbar/navbar.component';
import {SpeciesService} from "./common/services/species.service";

@NgModule({
    declarations: [
        AppComponent,
        PokedexComponent,
        PartyComponent,
        BattleComponent,
        NavbarComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    providers: [
        SpeciesService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
