import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {PokedexComponent} from './pokedex/pokedex.component';
import {PartyComponent} from './party/party.component';
import {BattleComponent} from './battle/battle.component';
import {routing} from './app.routing';

@NgModule({
    declarations: [
        AppComponent,
        PokedexComponent,
        PartyComponent,
        BattleComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
