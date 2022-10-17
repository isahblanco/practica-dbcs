package com.example.gestionPedidos.orders.Repository;

import com.example.gestionPedidos.orders.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findPedidoById(Integer id);

    boolean existsById(Integer id);

}
