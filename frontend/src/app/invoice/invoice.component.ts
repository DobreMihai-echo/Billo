import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InvoiceService } from '../services/invoice.service';
import { Invoice } from '../models/invoice.model';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.less'],
})
export class InvoiceComponent implements OnInit {
  id: number = 0;
  private sub: any;

  invoice: Invoice = {
    id: 0,
    invoiceName: '',
    receivedDate: new Date(),
    dueDate: new Date(),
    paid: 'no',
    value: '',
  };

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService
  ) {}

  ngOnInit(): void {
    this.sub = this.route.params.subscribe((params) => {
      this.id = +params['id'];
    });
    this.invoiceService.currentMessage.subscribe((message) => {
      this.invoice = message;
      console.log(message);
    });
  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
