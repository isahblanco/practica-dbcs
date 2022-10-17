package com.example.gestionPedidos.orders.Model;

import java.util.List;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author chrquin
 * @author mariher
 */
@Entity
@Table(name = "orderTable")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "deliveryAddress")
    private String deliveryAddress;

    @Basic(optional = false)
    @Column(name = "deliveryPostalCode")
    private String deliveryPostalCode;

    @Basic(optional = false)
    @Column(name = "deliveryCity")
    private String deliveryCity;

    @Basic(optional = false)
    @Column(name = "deliveryPerson")
    private String deliveryPerson;

    @Basic(optional = false)
    @Column(name = "idSeller")
    private Integer idSeller;

    @Basic(optional = true)
    @OneToMany(mappedBy = "fromOrderId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderLine> products;

    @Basic(optional = false)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "expecteDeliveryDate")
    private Date expectedDeliveryDate;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "cost")
    private Float cost;

    public Order() {
    }

    /**
     * Constructor de la clase Pedido.
     * 
     * @param deliveryAddress    La dirección de entrega.
     * @param deliveryPostalCode El código postal de la ciudad destino del pedido.
     * @param deliveryCity       La ciudad destino del pedido.
     * @param deliveryPerson     El nombre de la persona que recibirá el pedido.
     * @param idSeller           El identificador del vendedor asignado al pedido.
     * @param products           La lista de productos que contiene el pedido.
     */
    Order(String deliveryAddress, String deliveryPostalCode, String deliveryCity, String deliveryPerson,
            Integer idSeller, List<OrderLine> products) {

        this.deliveryAddress = deliveryAddress;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryCity = deliveryCity;
        this.deliveryPerson = deliveryPerson;
        this.idSeller = idSeller;
        this.products = products;
        this.status = OrderStatus.ORDERED;
        this.createdAt = new Date(new java.util.Date().getTime());
    }

    /**
     * Obtiene el id del pedido.
     * 
     * @return id del pedido.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Obtiene la dirección de entrega del pedido.
     * 
     * @return La dirección de entrega del pedido.
     */
    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    /**
     * Obtiene el código postal asignado al pedido.
     * 
     * @return El código postal asignado al pedido.
     */
    public String getDeliveryPostalCode() {
        return this.deliveryPostalCode;
    }

    /**
     * Obtiene la ciudad de entrega del pedido.
     * 
     * @return La ciudad de entrega del pedido.
     */
    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    /**
     * Obtiene el nombre de la persona que recibirá el pedido.
     * 
     * @return El nombre de la persona que recibirá el pedido.
     */
    public String getDeliveryPerson() {
        return this.deliveryPerson;
    }

    /**
     * Obtiene el identificador del vendedor asignado al pedido.
     * 
     * @return El identificador del vendedor asignado al pedido.
     */
    public Integer getIdSeller() {
        return this.idSeller;
    }

    /**
     * Obtiene la lista de productos del pedido.
     * 
     * @return La lista de productos del pedido.
     */
    public List<OrderLine> getProducts() {
        return this.products;
    }

    /**
     * Obtiene el estado del pedido.
     * 
     * @return El estado del pedido.
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
     * Obtiene la fecha de entrega provisional.
     * 
     * @return La fecha de entrega provisional.
     */
    public Date getExpectedDeliveryDate() {
        return this.expectedDeliveryDate;
    }

    /**
     * Obtiene la fecha de creación del pedido.
     * 
     * @return La fecha de creación del pedido.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Modifica la dirección de entrega.
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Modifica el código postal.
     */
    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    /**
     * Modifica la ciudad de entrega del pedido.
     */
    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    /**
     * Modifica el nombre de la persona que recibirá el pedido.
     */
    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    /**
     * Asigna el identificador del vendedor al pedido.
     */
    public void setIdSeller(Integer idSeller) {
        this.idSeller = idSeller;
    }

    /**
     * Modifica los productos del pedido.
     */
    public void setProducts(List<OrderLine> products) {
        this.products = products;
    }

    /**
     * Modifica el estado del pedido.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Modifica la fecha de entrega provisional.
     */
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    /**
     * Obtiene el coste del pedido.
     * 
     * @return el coste del pedido.
     */
    public Float getCost() {
        return this.cost;
    }

    /**
     * Modifica el coste del pedido.
     */
    public void setCost(Float cost) {
        this.cost = cost;
    }
}