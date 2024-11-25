import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerSummaryComponent } from './customer-summary/customer-summary.component';


export const routes: Routes = [
  { path: '', component: CustomerListComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'customers/:id', component: CustomerSummaryComponent },
];
