import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../services/data.service';
import { Customer } from '../models/customer';
import { AccountCreate } from '../models/account-create';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer-summaryView',
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './customer-summaryView.component.html',
  styleUrl: './customer-summaryView.component.css'
})
export class CustomerSummaryComponent implements OnInit {
  customer: Customer | undefined;
  customerId: number = -1;
  accountCreate: AccountCreate;

  constructor(
    private route: ActivatedRoute,
    private dataService: DataService
  ) {
    this.accountCreate = {initialCredit: 0};
  }

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

  onSubmit(form: any): void {
      if (form.valid) {
        this.dataService.addCredit(this.customerId, this.accountCreate).subscribe({
          next: (response) => {
            console.log('Credit added successfully:', response);
            form.reset();
            this.getCustomerDetails();
          },
          error: (err) => {
            console.error('Error adding credit:', err);
          },
        });
      }
    }
}