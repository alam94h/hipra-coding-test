import {Component, ViewChild} from '@angular/core';
import {Car} from "../../models/car";
import {NgClass, NgFor, NgIf, UpperCasePipe} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { CarService } from '../../services/car.service';
import {RouterLink} from "@angular/router";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import { Router } from '@angular/router';

@Component({
  selector: 'app-cars',
  standalone: true,
  imports: [
    UpperCasePipe,
    NgFor,
    FormsModule,
    NgIf,
    RouterLink,
    MatPaginatorModule,
    MatTableModule,
    MatProgressSpinnerModule,
    NgClass,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule
  ],
  templateUrl: './cars.component.html',
  styleUrl: './cars.component.css'
})
export class CarsComponent {
  // Variable que indica si les dades estan carregant
  loading = true;
  // Columnes que es mostren a la taula
  displayedColumns: string[] = ['name', 'carmaker','trims'];
  // Dades de la taula amb el tipus Car
  dataSource: MatTableDataSource<Car> = new MatTableDataSource<Car>();
  // Referència al paginador de la taula
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  cars: Car[] = [];

  constructor(private carService: CarService, private router: Router) {}

  ngOnInit(): void {
    // Obté els cotxes mitjançant el servei de cotxes
    this.carService.getCars().subscribe(cars => {
      this.dataSource.data = cars;
      this.dataSource.paginator = this.paginator;
      // Indica que les dades han acabat de carregar
      this.loading = false;
    });
  }

  // Mètode per aplicar un filtre a la taula
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  // Mètode que s'executa quan es fa clic a una fila de la taula
  onRowClick(car: Car) {
    this.router.navigate(['trims', car.name]);
  }
}
