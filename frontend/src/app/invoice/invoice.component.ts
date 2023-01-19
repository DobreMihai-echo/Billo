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

  private user = {
    id:0,
    username: 'username',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
  }

  invoice: Invoice = {
    id: 0,
    invoiceName: '',
    createdAt: new Date(),
    dueDate: new Date(),
    payed: true,
    price: 20.09,
    providerName:'',
    user: this.user,
    userName: '',
    userEmail:'',
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
