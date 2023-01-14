import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Invoice } from '../models/invoice.model';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  private messageSource = new BehaviorSubject<Invoice>({
    id: 0,
    invoiceName: '',
    receivedDate: new Date(),
    dueDate: new Date(),
    paid: 'no',
    value: '',
  });

  currentMessage = this.messageSource.asObservable();

  constructor() {}

  changeMessage(message: Invoice) {
    this.messageSource.next(message);
  }
}
