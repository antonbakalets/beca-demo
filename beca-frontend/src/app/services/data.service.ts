import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private apiCustomersUrl = '/api/v1/customers';

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiCustomersUrl);
  }

  getSummary(id: number): Observable<Customer> {
    return this.http.get<Customer>(this.apiCustomersUrl + '/' + id);
  }
}