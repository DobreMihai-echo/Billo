import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Invoice } from '../models/invoice.model';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  private user = {
    id:0,
    username: 'username',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
  }
  private messageSource = new BehaviorSubject<Invoice>({
    id: 0,
    invoiceName: '',
    createdAt: new Date(),
    dueDate: new Date(),
    payed: false,
    providerName: 'John Doe',
    price:23.09,
    user: this.user,
    userName:'',
    userEmail:''
  });

  currentMessage = this.messageSource.asObservable();

  constructor(private http:HttpClient) {}

  changeMessage(message: Invoice) {
    this.messageSource.next(message);
  }

  public getDataToPay(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`http://localhost:8070/api/v1/invoice/paid`)
  }

  public getDataPaid(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`http://localhost:8070/api/v1/invoice/unpaid`)
  }

  public pay(invoiceId: number): Observable<boolean> {
    return this.http.put<boolean>(`http://localhost:8070/api/v1/invoice/pay/${invoiceId}`,invoiceId);
  }
}
