import { Component, OnInit } from '@angular/core';
import { Order } from 'app/models/order.model';
import { OrderStatus } from 'app/models/orderStatus.model';
import { ClienteApiRestService } from 'app/services/cliente-api-rest.service';
import { DataService } from 'app/services/data.service';
import { concatAll } from 'rxjs/operators';

@Component({
  selector: 'app-order-listar',
  templateUrl: './order-listar.component.html',
  styleUrls: ['./order-listar.component.css']
})
export class OrderListarComponent implements OnInit {
  ORDERED = OrderStatus.ORDERED;
  IN_TRANSIT = OrderStatus.IN_TRANSIT;
  PROCESSING = OrderStatus.PROCESSING;
  DELIVERED = OrderStatus.DELIVERED;
  Order!: Order;
  Orders!: Order[];
  SingleOrder!: Order;
  mostrarMensaje!: boolean;
  mensaje!: string;
  
  constructor(private clienteApiRest: ClienteApiRestService, private datos: DataService) { }

  ngOnInit(): void {
    console.log("Dentro de listar pedidos.")
    
    this.datos.mensajeActual.subscribe(
      valor => this.mensaje = valor
    )
    console.log("Valor actual del mensaje: ") + this.mensaje;
      this.getOrders();
  }

  //Obtiene todos los pedidos registrados y los muestra.
  getOrders() {
    this.clienteApiRest.getAllOrders().subscribe(
      resp => {
        if (resp.status < 400) {
          this.Orders = resp.body as Order[];

        } else {
          this.mensaje = 'Error al acceder a los datos de pedidos';
          this.mostrarMensaje = true;
        }
      },
      err => {
        console.log("Error al traer la lista de pedidos: " + err.message);
        throw err;
      }
    )
  }
  //Borra un pedido concreto dado por id.
  borrarOrder(id: Number) {
    this.clienteApiRest.borrarOrder(String(id)).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          // Actualizamos la variable compartida
          this.mostrarMensaje = true;
          // Actualizamos variable compartida
          this.mensaje = resp.body; // Mostramos el mensaje retornado por el API
          // Actualizamos la lista de usuarios en la vista
          this.getOrders();
        } else {
          this.mostrarMensaje = true;
          this.mensaje = "Error al eliminar pedido";
        }
      },
      err => {
        console.log("Error al borrar: " + err.message);
        throw err;
      }
    )
  }

  //Obtiene un pedido concreto dado por id.
  getOrder(id:Number) {
    this.clienteApiRest.getOrder(String(id)).subscribe(
      resp => {
        if (resp.status < 400) {
          this.Order = resp.body as Order;
        } else {
          this.mensaje = 'Error al acceder a los datos de pedidos';
          this.mostrarMensaje = true;
        }
      },
      err => {
        console.log("Error al traer la lista de pedidos: " + err.message);
        throw err;
      }
    )
  }

  //Elimina los campos email y token del almacenamiento de sesión.
  logOut() {
    localStorage.clear();
  }
}
