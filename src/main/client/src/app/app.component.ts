import {Component} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Component({
    selector: 'app-root',
    template: `
<header>
  <app-navbar></app-navbar>
</header>

<main>
  <div class="container">
    <router-outlet></router-outlet>
  </div>
</main>

<footer></footer>
`,
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'app works!';
}
