package com.example.gestionPedidos.orders.Repository;

import com.example.gestionPedidos.orders.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductBySku(String sku);

}
