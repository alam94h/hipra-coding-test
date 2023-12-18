import {Component, Input, ViewChild} from '@angular/core';
import {TrimService} from "../../services/trim.service";
import {Router} from "@angular/router";
import {KeyValuePipe, Location, NgForOf, NgIf} from "@angular/common";
import {Trim} from "../../models/trim";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatListModule} from "@angular/material/list";
import {MatLineModule} from "@angular/material/core";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {StringUtils} from "../../utils/string-utils";

@Component({
  selector: 'app-trim-details',
  standalone: true,
  imports: [
    MatIconModule,
    MatTableModule,
    NgForOf,
    KeyValuePipe,
    NgIf,
    MatListModule,
    MatLineModule,
    MatPaginatorModule
  ],
  templateUrl: './trim-details.component.html',
  styleUrl: './trim-details.component.css'
})
export class TrimDetailsComponent {
  // Propietat d'entrada que rep l'ID del trim
  @Input() id?: number;
  pageSize = 10;
  pageIndex = 0;
  // Referència al paginador de la taula
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  trim: Trim | undefined;
  // Array que conté les propietats del Trim amb les etiquetes i els valors
  trimProperties: { label: string; value: any }[] = [];
  // Array que conté un subconjunt de les propietats del Trim per mostrar a la pàgina actual
  slicedTrimProperties: { label: string; value: any }[] = [];

  protected readonly RegExp = RegExp;

  constructor(private trimService: TrimService, private router: Router, private location: Location) {}

  ngOnInit(): void {
    // Obté les dades del trim mitjançant el servei de trims amb l'ID proporcionat
    this.trimService.getTrim(this.id).subscribe(trim => {
      this.trim = trim;
      // Si existeix el trim, crea un array de propietats amb etiquetes i valors, filtrant aquells amb valor null
      if (this.trim){
        this.trimProperties = Object.entries(this.trim).map(([label, value]) =>
          ({ label: StringUtils.capitalizeFirstLetter(label), value })).filter(property => property.value !== null);
      }
      // Actualitza les propietats del trim que es mostren a la pàgina actual
      this.updateTrimProperties();
    });
  }

  // Mètode que s'executa quan es canvia la pàgina del paginador
  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;

    this.updateTrimProperties();
  }

  // Mètode per actualitzar les propietats del trim que es mostren a la pàgina actual
  private updateTrimProperties(): void {
    // Calcula els índexs de les propietats a mostrar a la pàgina actual
    const startIndex = this.pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    // Obté un subconjunt de les propietats del trim basat en els índexs calculats
    this.slicedTrimProperties = this.trimProperties.slice(startIndex, endIndex);
  }

  // Mètode per tornar enrere a la pàgina anterior
  goBack(): void {
    this.location.back();
  }
}
