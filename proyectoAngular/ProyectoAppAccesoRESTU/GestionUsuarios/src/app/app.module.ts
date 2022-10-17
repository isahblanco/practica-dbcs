import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListarComponent } from './user-listar/user-listar.component';
import { UserEditarComponent } from './user-editar/user-editar.component';

import { ClienteApiRestService } from './services/cliente-api-rest.service';
import { DataService } from './services/data.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserAutenticarComponent } from './user-autenticar/user-autenticar.component';
import { OrderListarComponent } from './order-listar/order-listar.component';
import { OrderEditarComponent } from './order-editar/order-editar.component';

import { authInterceptorProviders } from './helpers/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    UserListarComponent,
    UserEditarComponent,
    UserAutenticarComponent,
    OrderListarComponent,
    OrderEditarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    ClienteApiRestService,
    DataService,
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
