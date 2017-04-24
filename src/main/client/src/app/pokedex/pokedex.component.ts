import { Component, OnInit } from '@angular/core';
import {SpeciesService} from "../common/services/species.service";

@Component({
  selector: 'app-pokedex',
  template: `
<div id="pokedex" class="container">
    <h2>Pokedex</h2>

    <table class="table">
        <thead>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Type 1</th>
            <th>Type 2</th>
        </tr>
        </thead>
        <tbody>
        <tr *ngFor="let species of speciesList">
            <td>
                <img src="/assets/images/pokemon/front/{{species.number}}.png" style="height:50px;">
            </td>
            <td>{{species.name}}</td>
            <td>{{species.type1}}</td>
            <td>{{species.type2}}</td>
        </tr>
        </tbody>
    </table>
</div>
`,
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {
  private speciesList: any[];

  constructor(private speciesService: SpeciesService) { }

  ngOnInit() {
    this.speciesService.getAllSpecies().subscribe(species => this.speciesList = species);
  }

}
