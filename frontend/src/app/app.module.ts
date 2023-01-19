import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { HomeComponent } from './home/home.component';
import { ConsumerComponent } from './consumer/consumer.component';
import { AdminComponent } from './admin/admin.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuthInterceptor } from './auth.interceptor';
import { AuthService } from './services/auth.service';
import { ConsumerAddOrderComponent } from './consumer-add-order/consumer-add-order.component';
import { HistoryComponent } from './history/history.component';
import { ProviderAddOrderComponent } from './provider-add-order/provider-add-order.component';
import { OrderAddComponent } from './order-add/order-add.component';
import { OrderUpdateComponent } from './order-update/order-update.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ProviderOrdersComponent } from './provider-orders/provider-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ConsumerComponent,
    AdminComponent,
    NavigationComponent,
    ConsumerAddOrderComponent,
    HistoryComponent,
    ProviderAddOrderComponent,
    OrderAddComponent,
    OrderUpdateComponent,
    OrderDetailsComponent,
    ProviderOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    AuthService
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
