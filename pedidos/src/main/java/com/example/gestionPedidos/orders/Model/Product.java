package com.example.gestionPedidos.orders.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productTable")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "sku")
    @Basic(optional = false)
    private String sku;

    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @Column(name = "price")
    @Basic(optional = false)
    private Float price;

    public Product() {

    }

    /**
     * Constructor de la clase Product.
     * 
     * @param sku   Un número o código asignado a un elemento para poder
     *              identificarlo
     *              en el inventario físico o financiero, así como para referencias
     *              otros tipos de servicios.
     *              (Ver https://es.wikipedia.org/wiki/Stock-keeping_unit)
     * @param name  El nombre del producto.
     * @param price El precio asignado al producto.
     */
    public Product(String sku, String name, Float price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }

    /**
     * Obtiene el sku del producto.
     * 
     * @return el sku del producto.
     */
    public String getSku() {
        return this.sku;
    }

    /**
     * Modifica el sku del producto.
     * 
     * @param sku el nuevo sku del producto.
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return el nombre del producto.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifica el nombre del producto.
     * 
     * @param name el nuevo nombre del producto.
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
     * @param price el nuevo precio del producto.
     */
    public void setPrice(Float price) {
        this.price = price;
    }

}