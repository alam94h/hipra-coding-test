import {Component, Input, ViewChild} from '@angular/core';
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {Car} from "../../models/car";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {Location, NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {Trim} from "../../models/trim";
import {TrimService} from "../../services/trim.service";

@Component({
  selector: 'app-trims',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatTableModule,
    NgIf
  ],
  templateUrl: './trims.component.html',
  styleUrl: './trims.component.css'
})
export class TrimsComponent {
  // Propietat d'entrada que rep el nom
  @Input() name?: String;
  // Flag que indica si està carregant les dades
  loading = true;
  // Columnes que es mostren a la taula
  displayedColumns: string[] = ['id', 'year', 'trim-details'];
  // Dades de la taula emmagatzemades en un MatTableDataSource
  dataSource: MatTableDataSource<Trim> = new MatTableDataSource<Trim>();
  // Referència al paginador de la taula
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  // Array que conté els trims obtinguts
  trims: Trim[] = [];

  constructor(private trimService: TrimService, private router: Router, private location: Location) {}

  ngOnInit(): void {
    // Obté els trims mitjançant el servei de trims amb el nom proporcionat
    this.trimService.getTrims(this.name).subscribe(trims => {
      // Assigna les dades obtingudes al dataSource de la taula
      this.dataSource.data = trims;
      // Assigna el paginador a la taula
      this.dataSource.paginator = this.paginator;
      // Finalitza l'estat de càrrega
      this.loading = false;
    });
  }

  // Mètode per aplicar un filtre a la taula quan es realitza una cerca
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  // Mètode que s'executa quan es fa clic a una fila de la taula
  onRowClick(trim: Trim) {
    // Navega a la pàgina de detalls del trim amb l'ID corresponent
    this.router.navigate(['trim-details', trim.id]);
  }

  // Mètode per tornar enrere a la pàgina anterior
  goBack(): void {
    this.location.back();
  }
}
