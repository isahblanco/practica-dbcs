import { Component, OnInit } from '@angular/core';
import { User } from '../models/app.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteApiRestService } from '../services/cliente-api-rest.service';
import { Observable } from 'rxjs';
import { DataService } from '../services/data.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from 'app/services/auth-service.service';

@Component({
  selector: 'app-user-autenticar',
  //templateUrl: './user-autenticar.component.html',
  styleUrls: ['./user-autenticar.component.css'],
  template: `
  <form [formGroup]="form">
      <fieldset>
          <legend>Login</legend>
          <div class="form-field">
              <label>Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
              <input name="email" formControlName="email">
          </div>
          <div class="form-field">
              <label>Password:&nbsp;</label>
              <input name="password" formControlName="password" 
              type="password">
          </div>
      </fieldset>
      <div class="form-buttons">
          <button class="btn btn-primary" 
                  (click)="login()">Login</button>
      </div>
  </form>`})

export class UserAutenticarComponent implements OnInit {
  form: FormGroup;
  constructor(private fb:FormBuilder, private authService: AuthServiceService, private router: Router ) {
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  
  ngOnInit(): void {
    console.log("Dentro de login");

  }

  login() {
    const val = this.form.value;

    if(val.email && val.password) {
      this.authService.login(val.email, val.password).subscribe(
        () => {
          console.log("El usuario se ha identificado.");
          this.router.navigateByUrl('/users');
        }
      );
    }
  }
}