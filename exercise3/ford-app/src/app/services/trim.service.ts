import { Injectable } from '@angular/core';
import {from, map, Observable, of} from "rxjs";
import {Trim} from "../models/trim";
import $ from "jquery";
import {Car} from "../models/car";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TrimService {

  private apiURL = 'http://www.carqueryapi.com/api/0.3/?callback=?';

  private trims: any;

  constructor(private http: HttpClient) { }

  // Mètode per obtenir la llista de trims d'un model específic
  getTrims(name: any): Observable<Trim[]> {
    // Utilitza jQuery per fer una crida a l'API amb paràmetres específics
    return from($.getJSON(this.apiURL, {cmd: "getTrims", model: name}))
      .pipe(
        // Mapeja les dades obtingudes a un array de trims
        map((data: any) => {
          const trims: Trim[] = data['Trims'].map((trim: any) => ({
            // Mapeja cada atribut del trim
            id: trim.model_id,
            year: trim.model_year,
            engine_position: trim.model_engine_position,
            engine_cc: trim.model_engine_cc,
            engine_cy1: trim.model_engine_cyl,
            engine_type: trim.model_engine_type,
            engine_valves_per_cy1: trim.model_engine_valves_per_cyl,
            engine_power_ps: trim.model_engine_power_ps,
            engine_power_rpm: trim.model_engine_power_rpm,
            engine_torque_nm: trim.model_engine_torque_nm,
            engine_bore_mm: trim.model_engine_bore_mm,
            engine_stroke_mm: trim.model_engine_stroke_mm,
            drive: trim.model_drive,
            transmission_type: trim.model_transmission_type,
            seats: trim.model_seats,
            weight_kg: trim.model_weight_kg,
            length_mm: trim.model_length_mm,
            width_mm: trim.model_width_mm,
            height_mm: trim.model_height_mm,
            wheelbase_mm: trim.model_wheelbase_mm,
            fuel_cap_1: trim.model_fuel_cap_l,
            sold_in_us: trim.model_sold_in_us,
            make_country: trim.make_country
          }));
          // Guarda els trims a la variable de la classe
          this.setTrims(trims)
          return trims;
        })
      );
  }

  // Mètode per establir els trims
  setTrims(data: any): void {
    this.trims = data;
  }

  // Mètode per obtenir la informació d'un trim específic
  getTrim(id: number | undefined): Observable<Trim | undefined> {
    // Busca el trim amb l'ID proporcionat a la llista de trims
    const trim = this.trims.find((t: { id: number; }) => t.id === id);
    return of(trim);
  }
}
