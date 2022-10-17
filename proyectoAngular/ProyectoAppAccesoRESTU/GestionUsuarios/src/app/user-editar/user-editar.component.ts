import { Component, OnInit } from '@angular/core';
import { User } from '../models/app.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteApiRestService } from '../services/cliente-api-rest.service';
import { Observable } from 'rxjs';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-user-editar',
  templateUrl: './user-editar.component.html',
  styleUrls: ['./user-editar.component.css']
})
export class UserEditarComponent implements OnInit {

  usuarioVacio = { 
    id: 0,
    name: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    enabled: new Boolean,
    createdAt: new Date,
    updatedAt: new Date 
  };

  user = this.usuarioVacio as User; // Inicializamos el user
  
  id!: String;
  operacion!: String;

  // Inyectamos la ruta activa, servicio de enrutamiento, servicio de acceso al servicio REST cliente-api-rest
  // y el servicio para la compartición de mensajes entre componentes.
  constructor(private ruta: ActivatedRoute, private router: Router, private clienteApiRest: ClienteApiRestService,
    private datos: DataService) { }

  ngOnInit() {
    console.log("En editar-user");

    this.operacion = this.ruta.snapshot.url[this.ruta.snapshot.url.length - 1].path;
    
    if(this.operacion == "editar") { // Si se cumple, trae el JSON con el usuario a editar
                                     // Si no, será un nuevo usuario y el formulario estará vacío
      this.ruta.paramMap.subscribe(
        params => {
          this.id = params.get('id')!;
        },
        err => console.log("Error al leer id para editar : " + err)
      )
      this.clienteApiRest.getUser(this.id).subscribe(
        resp => {
          this.user = resp.body!;
        },
        err => {
          console.log("Error al traer el usuario: " + err.message);
          throw err;
        }
      )
    }
  }

  onSubmit() {
    console.log("Enviado formulario");
    if (this.id) { // Si existe el id estamos en edicion, si no en añadir
      this.clienteApiRest.modificarUser(String(this.user.id), this.user).subscribe(
        resp => {
          if (resp.status < 400) {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje(resp.body);
          } else {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje("Error al modificar usuario.");
          }
          this.router.navigate(['users']);
        },
        err => {
          console.log("Error al editar: " + err.message);
          throw err;
        }
      )
    } else { // En añadir/crear un nuevo usuario
      this.clienteApiRest.crearUser(this.user).subscribe(
        resp => {
          if (resp.status < 400) {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje(resp.body);
          } else {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje("Error al añadir usuario.");
          }
          this.router.navigate(['users']);
        },
        err => {
          console.log("Error al editar: " + err.message);
          throw err;
        }
      )
    } 
  }
}
