import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserListarComponent } from './user-listar/user-listar.component';
import { UserEditarComponent } from './user-editar/user-editar.component';
import { UserAutenticarComponent } from './user-autenticar/user-autenticar.component';
import { AuthGuardService } from './services/auth-guard.service';
import { OrderListarComponent } from './order-listar/order-listar.component';
import { OrderEditarComponent } from './order-editar/order-editar.component';

const routes: Routes = [
  {path: 'login', component:UserAutenticarComponent},
  {path: 'users', component:UserListarComponent, canActivate:[AuthGuardService]},
  {path: 'users/nuevo', component:UserEditarComponent, canActivate:[AuthGuardService]},
  {path: 'users/:id/editar', component:UserEditarComponent, canActivate:[AuthGuardService]},
  {path: 'orders', component:OrderListarComponent, canActivate:[AuthGuardService]},
  {path: 'orders/nuevo', component:OrderEditarComponent, canActivate:[AuthGuardService]},
  {path: 'orders/:id/editar', component:OrderEditarComponent, canActivate:[AuthGuardService]},
  {path: '**', redirectTo:'login', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
