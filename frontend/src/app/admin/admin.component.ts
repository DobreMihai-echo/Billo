import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { AdminServiceService } from '../services/admin-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public providers: User[];

  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {
    this.getProviders();
  }

  public getProviders(): void {
    this.adminService.getProviders().subscribe(
      (response: User[]) => {
        this.providers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public async onApproveProvider(provider: User)  {
    this.adminService.acceptProviders(provider).subscribe(
      (response) => {
        console.log(response);
        const updatedProviders = this.providers.filter(invoiceObj => invoiceObj.id != provider.id);
        console.log(updatedProviders);
        this.providers = updatedProviders;
        console.log(response);
      }
    );
  }

  public onRejectProvider(provider: User) : void {
    this.adminService.rejectProvider(provider.id).subscribe(
      (response) => {
        const updatedProviders = this.providers.filter(invoiceObj => invoiceObj.id != provider.id);
        this.providers = updatedProviders;
        console.log(response);
      }
    );
  }

}
