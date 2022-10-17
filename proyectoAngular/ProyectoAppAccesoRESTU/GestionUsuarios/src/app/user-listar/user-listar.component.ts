import { Component, OnInit } from '@angular/core';
import { ClienteApiRestService } from '../services/cliente-api-rest.service';
import { User } from '../models/app.model';
import { DataService } from '../services/data.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-user-listar',
  templateUrl: './user-listar.component.html',
  styleUrls: ['./user-listar.component.css']
})
export class UserListarComponent implements OnInit {

  Users!: User[];
  SingleUser!: User;
  mostrarMensaje!: boolean;
  mensaje!: string;
  enabledControl:FormControl = new FormControl()
  listaIds:FormControl = new FormControl()
  emailDelUser:FormControl = new FormControl()

  // Inyectamos los servicios
  constructor(private clienteApiRest: ClienteApiRestService, private datos: DataService) { }

  ngOnInit(): void {

    console.log("Dentro funcion ngOnInit de Listar");

    this.datos.mostrarMensajeActual.subscribe(
      valor => this.mostrarMensaje = valor
    )
    console.log("Valor actual de si mostrar mensaje: " + this.mostrarMensaje);

    this.datos.mensajeActual.subscribe(
      valor => this.mensaje = valor
    )
    console.log("Valor actual del mensaje: " + this.mensaje);

    this.getUsers_AccesoResponse("Todos");
  }

  getUsers_AccesoResponse(filtro : String) { // Le pasamos el String del filtro
    this.clienteApiRest.getAllUsers_ConResponse(filtro).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          this.Users = resp.body as User[]; // Se accede al cuerpo de la respuesta
        } else {
          this.mensaje = 'Error al acceder a los datos';
          this.mostrarMensaje = true;
        }
      },
      err => {
        console.log("Error al traer la lista: " + err.message);
        throw err;
      }
    )
  }

  borrar(id: Number) { // Le pasamos el id del usuario a borrar
    this.clienteApiRest.borrarUser(String(id)).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          // Actualizamos la variable compartida
          this.mostrarMensaje = true;
          // Actualizamos variable compartida
          this.mensaje = resp.body; // Mostramos el mensaje retornado por el API
          // Actualizamos la lista de usuarios en la vista
          this.getUsers_AccesoResponse("Todos");
        } else {
          this.mostrarMensaje = true;
          this.mensaje = "Error al eliminar registro";
        }
      },
      err => {
        console.log("Error al borrar: " + err.message);
        throw err;
      }
    )
  }

  habilitarUsers(listaIds: String[]) { // Le pasamos el array de Strings con los ids
    this.clienteApiRest.enableUsers(listaIds).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          this.mostrarMensaje = true;
          // Actualizamos la lista de usuarios en la vista
          this.getUsers_AccesoResponse("Todos");
        } else {
          this.mostrarMensaje = true;
          this.mensaje = "Error al habilitar usuarios";
        }
      },
      err => {
        console.log("Error al habilitar usuario/s: " + err.message);
        throw err;
      }
    )
  }

  userEmail(email: String) {
    this.clienteApiRest.getUserPorEmail(email).subscribe(
      resp => {
        if (resp.status < 400) {
          this.Users = resp.body as User[];
        } else {
          this.mostrarMensaje = true;
          this.mensaje = "No hay un usuario con ese email.";
        }
      }
    )
    console.log("Llego aquí?");
  }

  logOut() {
    localStorage.clear();
  }
}
