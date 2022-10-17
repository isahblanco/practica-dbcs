import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router : Router, 
    private authService : AuthServiceService) { }

  canActivate(ruta: ActivatedRouteSnapshot, estado: RouterStateSnapshot) {
    if(this.authService.estaLogin())
    return true;

    this.router.navigate(['login']);
    return false;
  }
}
