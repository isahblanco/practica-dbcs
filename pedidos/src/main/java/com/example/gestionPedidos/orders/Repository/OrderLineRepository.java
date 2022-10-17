package com.example.gestionPedidos.orders.Repository;

import com.example.gestionPedidos.orders.Model.OrderLine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    // void deleteByFromOrderId( fromOrderId);
}
