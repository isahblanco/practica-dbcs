import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { User } from '../models/app.model';
import { Observable } from 'rxjs';
import { Order } from 'app/models/order.model';
import { OrderLine } from 'app/models/orderLine.model';
import {Product } from '../models/product.model';

@Injectable({ 
  providedIn: 'root' 
})

export class ClienteApiRestService {

  private static readonly USER_URI = 'http://localhost:8000/api/gestor/';
  private static readonly ORDER_URI = 'http://localhost:8000/api/pedidos/';
  constructor(private http: HttpClient) { } //Inyección del servicio HttpClient.
    
  /**
   *
   * Relativo a Users
   *  
   */

  // Obtiene a todos los usuarios en función del parametro filtro
  getAllUsers_ConResponse(filtro: String): Observable<HttpResponse<User[]>> {
    let url ;
    if (filtro == "Inhabilitados") {     // Muestra a los inhabilitados
      url = ClienteApiRestService.USER_URI + "?enable=false"; 
    } else if (filtro == "Habilitados"){ // A los habilitados
      url = ClienteApiRestService.USER_URI + "?enable=true";
    } else {                             // A todos
      url = ClienteApiRestService.USER_URI;
    }
    return this.http.get<User[]>(url, { observe: 'response'});
  }

  // Borra a un usuario dado el id
  borrarUser(id: String): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.USER_URI + id;
    return this.http.delete(url, { observe: 'response', responseType: 'text' });
  }

  // Crea a un nuevo usuario dado un usuario
  crearUser(user: User): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.USER_URI;
    return this.http.post(url, user, { observe: 'response', responseType: 'text' });
  }

  // Modifica a un usuario dado su id y el propio usuario
  modificarUser(id: String, user: User): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.USER_URI + id;
    return this.http.put(url, user, { observe: 'response', responseType: 'text' });
  }

  // Obtiene a un usuario dado su id
  getUser(id: String): Observable<HttpResponse<User>> {
    let url = ClienteApiRestService.USER_URI + id;
    return this.http.get<User>(url, { observe: 'response' });
  }
  
  // Habilita a uno a más usuarios, dada una lista con los ids
  enableUsers(user_id: String[]){
    let url = ClienteApiRestService.USER_URI + "enable" + "?user_id=" + user_id; 
    return this.http.put(url, user_id, { observe: 'response', responseType: 'text'});
  }

  // Obtiene un usuario dado el email
  getUserPorEmail(email: String): Observable<HttpResponse<User[]>>{
    let url = ClienteApiRestService.USER_URI + "email" + "?email=" + email;
    return this.http.get<User[]>(url, { observe: 'response'});
  }
  // Obtiene el id de un usuario dado el email
  getIdPorEmail(email : String): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.USER_URI + "getId" + "?email=" + email;
    return this.http.get<String>(url, { observe: 'response' });
  }

  /**
   * Relativo a Orders
   * 
   */

  // Obtiene todos los pedidos
  getAllOrders(): Observable<HttpResponse<Order[]>> {
    let url = ClienteApiRestService.ORDER_URI;
    return this.http.get<Order[]>(url, { observe: 'response'});
  }

  // Obtiene un pedido por su id
  getOrder(id: String): Observable<HttpResponse<Order>> {
    let url = ClienteApiRestService.ORDER_URI + id ;
    return this.http.get<Order>(url, {observe: 'response'});
  }

  // Crea un nuevo pedido dado un pedido
  crearOrder(order: Order): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.ORDER_URI;
    return this.http.post(url, order, { observe: 'response', responseType: 'text' });
  }

  // Modidifica un pedido dado su id y el pedido
  modificarOrder(id: String, order: Order): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.ORDER_URI +id;
    return this.http.put(url, order, { observe: 'response', responseType: 'text' });
  }

  // Borra un pedido dado su id
  borrarOrder(id: String): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.ORDER_URI + id;
    return this.http.delete(url, { observe: 'response', responseType: 'text' });
  }

  // Crea una nueva línea de pedido dada una línea de pedido
  crearNuevaOrderLine(orderLine: OrderLine):Observable<HttpResponse<any>>{
    let url = ClienteApiRestService.ORDER_URI + "orderLine";
    return this.http.post(url, orderLine, { observe: 'response', responseType: 'text' });
  }

  // Borra una línea de pedido dado su id
  borrarOrderLine(id: String): Observable<HttpResponse<any>> {
    let url = ClienteApiRestService.ORDER_URI + "orderLine/" + id;
    return this.http.delete(url, { observe: 'response', responseType: 'text' });
  }

  // Obtiene todos los productos a elegir
  getAllProducts(): Observable<HttpResponse<Product[]>> {
    let url = ClienteApiRestService.ORDER_URI + "products";
    return this.http.get<Product[]>(url, { observe: 'response'});
  }
}