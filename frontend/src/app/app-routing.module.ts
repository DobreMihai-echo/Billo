import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';
import { ConsumerAddOrderComponent } from './consumer-add-order/consumer-add-order.component';
import { ConsumerComponent } from './consumer/consumer.component';
import { HistoryComponent } from './history/history.component';
import { HomeComponent } from './home/home.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { LoginComponent } from './login/login.component';
import { OrderAddComponent } from './order-add/order-add.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { OrderUpdateComponent } from './order-update/order-update.component';
import { ProviderAddOrderComponent } from './provider-add-order/provider-add-order.component';
import { ProviderOrdersComponent } from './provider-orders/provider-orders.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: '',component: LoginComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent , canActivate:[AuthGuard], data:{roles:['ROLE_ADMIN']}},
  { path: 'consumer', component: ConsumerComponent , canActivate:[AuthGuard], data:{roles:['ROLE_CONSUMER']}},
  { path: 'orders', component: ConsumerAddOrderComponent , canActivate:[AuthGuard], data:{roles:['ROLE_CONSUMER']}},
  { path: 'history', component: HistoryComponent , canActivate:[AuthGuard], data:{roles:['ROLE_CONSUMER']}},
  { path: 'invoice/:id', component: InvoiceComponent },
  { path: 'provider', component: ProviderOrdersComponent , canActivate:[AuthGuard], data:{roles:['ROLE_PROVIDER']}},
  {path: 'create-order', component: OrderAddComponent , canActivate:[AuthGuard], data:{roles:['ROLE_PROVIDER']}},
  {path: 'update-order/:id', component: OrderUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
