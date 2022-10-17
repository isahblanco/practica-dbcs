package com.example.gestionPedidos.orders.Model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author chrquin
 * @author mariher
 */
@Entity
@Table(name = "orderLine")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn(name = "fromOrderId", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Order fromOrderId;

    @Basic(optional = false)
    private String sku;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "price")
    private Float price;

    @Column(nullable = false)
    private Integer units;

    public OrderLine() {
    }

    /**
     * Constructor de la clase OrderLine, que corresponde a una línea de pedido.
     * 
     * @param sku   Un número o código asignado a un elemento para poder
     *              identificarlo
     *              en el inventario físico o financiero, así como para referencias
     *              otros tipos de servicios.
     *              (Ver https://es.wikipedia.org/wiki/Stock-keeping_unit)
     * @param name  El nombre del producto.
     * @param price El precio asignado al producto.
     * @param units La cantidad de unidades solicitadas del producto.
     */
    OrderLine(String sku, String name, Float price, Integer units) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.units = units;
    }

    public Integer getId() {
        return this.id;
    }

    /**
     * Obtiene el sku del producto correspondiente a la línea de pedido.
     * 
     * @return el sku del producto.
     */
    public String getSku() {
        return this.sku;
    }

    /**
     * Modifica el sku del producto.
     * 
     * @param sku El nuevo sku.
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Obtiene el nombre del producto
     * 
     * @return el nombre del producto.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifica el nombre del producto.
     * 
     * @param name El nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return el precio del producto.
     */
    public Float getPrice() {
        return this.price;
    }

    /**
     * Modifica el precio del producto.
     * 
     * @param price el nuevo precio.
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Obtiene las unidades del producto.
     * 
     * @return las unidades del producto.
     */
    public Integer getUnits() {
        return this.units;
    }

    /**
     * Modifica las unidades del producto.
     * 
     * @param units la nueva cantidad.
     */
    public void setUnits(Integer units) {
        this.units = units;
    }

    public Order getFromOrderId() {
        return this.fromOrderId;
    }

}
