import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Injectable()
export class SpeciesService {

  constructor(private http: Http) { }

  getAllSpecies(): Observable<any[]> {
    return this.http.get('api/species')
        .map(response => response.json())
        .catch(error => {
          return Observable.throw(error);
        });
  }
}
