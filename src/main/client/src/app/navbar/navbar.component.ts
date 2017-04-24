import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-navbar',
    template: `
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" [routerLink]="['']">
        Pokemon
      </a>
    </div>
    <ul class="nav navbar-nav">
      <li routerLinkActive="active">
        <a [routerLink]="['/pokedex']">Pokedex</a>
      </li>
      <li routerLinkActive="active">
        <a [routerLink]="['/party']">Party</a>
      </li>
      <li routerLinkActive="active">
        <a [routerLink]="['/battle']" >Battle</a>
      </li>
    </ul>
  </div>
</nav>
`,
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
