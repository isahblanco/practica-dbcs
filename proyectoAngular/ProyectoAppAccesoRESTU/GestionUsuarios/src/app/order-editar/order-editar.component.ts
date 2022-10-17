import { Component, OnInit } from '@angular/core';
import { Order } from 'app/models/order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteApiRestService } from 'app/services/cliente-api-rest.service';
import { Observable } from 'rxjs';
import { OrderLine } from 'app/models/orderLine.model';
import { OrderStatus } from 'app/models/orderStatus.model';
import { DataService } from 'app/services/data.service';
import { FormControl } from '@angular/forms';
import { Product } from 'app/models/product.model';

@Component({
  selector: 'app-order-editar',
  templateUrl: './order-editar.component.html',
  styleUrls: ['./order-editar.component.css']
})
export class OrderEditarComponent implements OnInit {

productsControl:FormControl = new FormControl();

  pedidoVacio = {
      id: 0,
      deliveryAddress: "",
      deliveryPostalCode: "",
      deliveryCity: "",
      deliveryPerson: "",
      idSeller: 0,
      products: new Array,
      status: OrderStatus.ORDERED,
      createdAt: new Date,
      expectedDeliveryDate: new Date, 
      cost : 0.0
  };

  orderLineVacio = {
    id: 0,
    fromOrderId: Object,
    sku : "",
    name : "",
    price: 0.0,
    units: 0
  };

  order = this.pedidoVacio as Order;
  orderLine = this.orderLineVacio as OrderLine;

  id!: String;
  operacion! : String;
  orderLines!: OrderLine[];
  mostrarMensaje!: boolean;
  mensaje!: string;
  products!: Product[];
  product!: Product;
  stat!: OrderStatus;
  statusForm!: FormControl; 
  OrderStatus = OrderStatus; //Referencia al tipo del enum
  costeTotal! : Number;
  isVisible!:any;
  idSeller!: String;

  constructor(private ruta: ActivatedRoute, private router: Router, private clienteApiRest: ClienteApiRestService,
    private datos: DataService) { }

  ngOnInit(): void {
    console.log("En order-editar");

    this.operacion = this.ruta.snapshot.url[this.ruta.snapshot.url.length - 1].path;
    this.getProducts();
    this.isVisible = false;
    this.getIdSeller(String(localStorage.getItem("email")));

    if(this.operacion == "editar") {
      this.isVisible = true;
      this.ruta.paramMap.subscribe (
        params => {
          this.id = params.get('id')!; 
        },
        err => console.log("Error al leer el id para editar: " + err)
      )
      this.clienteApiRest.getOrder(this.id).subscribe(
        resp => {
          this.order = resp.body!;
        },
        err => {
          console.log("Error al ver el pedido: " + err.message);
          throw err;
        }
      )
    } 
  }

   //Obtenemos el id del usuario logeado
  getIdSeller(email: String){
    this.clienteApiRest.getIdPorEmail(email).subscribe(
      resp => {
        if (resp.status < 400) {
          this.idSeller = resp.body as String;
          //Solo si el order idSeller es 0, se guarda el del usuario logeado
          if(this.order.idSeller == 0){
            this.order.idSeller = Number(this.idSeller);
          }
        } else {
          this.mensaje = 'Error al acceder a los datos del usuario';
          this.mostrarMensaje = true;
        }
      },
      err => {
        console.log("Error al obtener el id del usuario: " + err.message);
        throw err;
      }
    )
  }

  //Obtiene los productos a elegir
  getProducts(){
    this.clienteApiRest.getAllProducts().subscribe(
      resp => {
        if (resp.status < 400) {
          this.products = resp.body as Product[];
        } else {
          this.mensaje = 'Error al acceder a los datos de pedidos';
          this.mostrarMensaje = true;
        }
      },
      err => {
        console.log("Error al traer la lista de productos: " + err.message);
        throw err;
      }
    )
  }

  //Modifica el estado del pedido
  modificarEstado(estado:string) {
    const st = estado;
    this.order.status = st as OrderStatus;
  }

  //Crea una nueva línea de producto
  crearNuevaOrderLine(unidades: string){

    this.orderLine.fromOrderId = this.order;
    this.orderLine.sku = this.product.sku;
    this.orderLine.name = this.product.name;
    this.orderLine.price = this.product.price;
    this.orderLine.units = Number(unidades);

    this.clienteApiRest.crearNuevaOrderLine(this.orderLine).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          // Actualizamos la variable compartida
          this.mostrarMensaje = true;
          // Actualizamos variable compartida
          this.mensaje = resp.body; // Mostramos el mensaje retornado por el API
          //Actualizamos la vista
          this.ngOnInit();
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

  //Borra la línea de producto correspondiente
  borrarOrderLine(idOrderLine: Number) { // Le pasamos el id de la linea de pedido
    this.clienteApiRest.borrarOrderLine(String(idOrderLine)).subscribe(
      resp => {
        if (resp.status < 400) { // Si no hay error en la respuesta
          // Actualizamos la variable compartida
          this.mostrarMensaje = true;
          // Actualizamos variable compartida
          this.mensaje = resp.body; // Mostramos el mensaje retornado por el API
          //Actualizamos 
          this.ngOnInit();
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

  onSubmit() {
    console.log("Formulario de pedido");
    if(this.id) { //Si existe, lo editamos. Si no, se crea.
      this.isVisible = true;
      var acumulador = 0;
      for(var i = 0; i < this.order.products.length; i++) {
        var precio = this.order.products[i].price;
        var cantidad = this.order.products[i].units;
        acumulador = acumulador + (+precio * +cantidad); 
      }
      this.order.cost = acumulador;
      this.clienteApiRest.modificarOrder(String(this.order.id), this.order).subscribe(
        resp => {
          if (resp.status < 400) {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje(resp.body);
          } else {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje("Error al modificar el pedido.");
          }
          this.router.navigate(['orders']);
        },
        err => {
          console.log("Error al editar: " + err.message);
          throw err;
        }
      )
    } else { //Para crear un nuevo pedido
      this.clienteApiRest.crearOrder(this.order).subscribe(
        resp => {
          if (resp.status < 400) {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje(resp.body);
          } else {
            this.datos.cambiarMostrarMensaje(true);
            this.datos.cambiarMensaje("Error al añadir un pedido.");
          }
          this.router.navigate(['orders']);
        },
        err => {
          console.log("Error al crear: " + err.message);
          throw err;
        }
      )
    }
  }
}
