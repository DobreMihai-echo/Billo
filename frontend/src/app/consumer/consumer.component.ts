import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Invoice } from '../models/invoice.model';
import { InvoiceService } from '../services/invoice.service';
import { UserService} from '../services/user-service.service';
import { Order } from '../models/orders';

@Component({
  selector: 'app-consumer',
  templateUrl: './consumer.component.html',
  styleUrls: ['./consumer.component.less'],
})
export class ConsumerComponent implements OnInit {
  invoices: Invoice[];

  private user = {
    id:0,
    username: 'username',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
  }

  message: Invoice = {
    id: 0,
    invoiceName: '',
    createdAt: new Date(),
    dueDate: new Date(),
    payed: true,
    price: 20.09,
    providerName:'',
    user: this.user,
    userName:'',
    userEmail:''
  };

  orders: Array<Order> = [];

  constructor(private invoiceService: InvoiceService, private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    this.invoiceService.currentMessage.subscribe((message) => {
      this.message = message;
    });
    this.getAllUnpaidInvoices();
  }
  goToInvoice(invoice: Invoice, id: any,event:any) {
    this.invoiceService.changeMessage(invoice);
    this.router.navigate(['/invoice', id]);
  }

  pay(id: any,event:any) {
    console.log(id)
    event.cancelBubble = true;
    this.invoiceService.pay(id).subscribe((response:any) => {
      const updatedInvoices = this.invoices.filter(invoiceObj => invoiceObj.id != id);
      this.invoices = updatedInvoices;
      console.log(response);
    })
  }

  getAllUnpaidInvoices() {
    this.invoiceService.getDataToPay().subscribe((response:any) => {
      this.invoices = response;
      console.log(response);
    })
  }
}
