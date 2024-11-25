import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { Customer } from '../models/customer';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-customer-summary',
  imports: [NgFor, NgIf],
  templateUrl: './customer-summary.component.html',
  styleUrl: './customer-summary.component.css'
})
export class CustomerSummaryComponent implements OnInit {
  customer: Customer | undefined;
  customerId: number = -1;

  constructor(
    private route: ActivatedRoute,
    private dataService: DataService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.customerId = parseInt(params.get('id')!);
      this.getCustomerDetails();
    });
  }

  getCustomerDetails(): void {
    this.dataService.getSummary(this.customerId).subscribe({
      next: (data) => this.customer = data,
      error: (err) => console.error('Error fetching customer details', err),
    });
  }
}