import { Injectable } from '@angular/core';
import { Car } from "../models/car";
import {from, map, Observable, of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import $ from "jquery";
import {Trim} from "../models/trim";
import {StringUtils} from "../utils/string-utils";

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiURL = 'http://www.carqueryapi.com/api/0.3/?callback=?';
  constructor(private http: HttpClient) { }

  // Mètode per obtenir la llista de cotxes
  getCars(): Observable<Car[]> {
    // Utilitza jQuery per fer una crida a l'API amb paràmetres específics
    return from($.getJSON(this.apiURL, {cmd: "getModels", make: "ford"}))
      .pipe(
        // Mapeja les dades obtingudes a un array de cotxes
        map((data: any) => {
          const cars: Car[] = data['Models'].map((model: any) => ({
            name: model.model_name,
            carmaker: StringUtils.capitalizeFirstLetter(model.model_make_id)
          }));
          return cars;
        })
      );
  }
}
