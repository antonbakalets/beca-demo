import { Component } from '@angular/core';
import { DataService } from './../services/data.service';
import { NgFor, NgIf } from '@angular/common';
import { Customer } from '../models/customer';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  imports: [NgFor, NgIf, RouterModule],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})
export class CustomerListComponent {

  customers: Customer[] = []; // Array to store fetched items

  constructor(private dataService: DataService) {}

  ngOnInit(): void {
    this.dataService.getCustomers().subscribe({
      next: (data) => (this.customers = data),
      error: (err) => console.error('Error fetching items', err),
    });
  }
}
