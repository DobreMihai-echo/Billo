import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Invoice } from '../models/invoice.model';
import { InvoiceService } from '../services/invoice.service';

@Component({
  selector: 'app-consumer',
  templateUrl: './consumer.component.html',
  styleUrls: ['./consumer.component.less'],
})
export class ConsumerComponent implements OnInit {
  invoices: Invoice[] = new Array(50).fill(1).map((_, i) => ({
    id: i,
    invoiceName: `invoice-${i}`,
    receivedDate: new Date(),
    dueDate: new Date(),
    paid: Math.random() > 0.5 ? 'yes' : 'no',
    value: `${Math.floor(Math.random() * 150)}$`,
  }));

  message: Invoice = {
    id: 0,
    invoiceName: ``,
    receivedDate: new Date(),
    dueDate: new Date(),
    paid: 'no',
    value: '',
  };

  constructor(private invoiceService: InvoiceService, private router: Router) {}

  ngOnInit(): void {
    this.invoiceService.currentMessage.subscribe((message) => {
      this.message = message;
    });
  }
  goToInvoice(invoice: any, id: any) {
    this.invoiceService.changeMessage(invoice);
    this.router.navigate(['/invoice', id]);
  }
}
