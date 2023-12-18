import { Routes } from '@angular/router';
import { CarsComponent } from "./pages/cars/cars.component";
import {TrimsComponent} from "./pages/trims/trims.component";
import {TrimDetailsComponent} from "./pages/trim-details/trim-details.component";

export const routes: Routes = [
  { path: 'cars', component: CarsComponent },
  { path: 'trims/:name', component: TrimsComponent },
  { path: 'trim-details/:id', component: TrimDetailsComponent },
  { path: "**", redirectTo: "cars" },
];
