import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ClienteApiRestService } from './cliente-api-rest.service';
import { User } from 'app/models/app.model';
import { Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private static readonly URI = 'http://localhost:8000/api/autenticar';

  constructor(private http: HttpClient, private clienteApiRest: ClienteApiRestService) { }

  login(email: string, password: string) {
    let url = AuthServiceService.URI;
    return this.http
    .post<any>(url, {email, password})
    .pipe(
      map(datosUser => {
        localStorage.setItem("email", email);
        localStorage.setItem("token", datosUser.token);
        return datosUser;
      })
    );
  }

  estaLogin() {
    let user = localStorage.getItem("email");
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    localStorage.removeItem("email");
    localStorage.removeItem("token");
  }
}