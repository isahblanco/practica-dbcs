package com.example.gestionPedidos.orders.Controller;

import java.sql.Date;
import java.time.LocalDate;

import com.example.gestionPedidos.orders.Exception.OrderException;
import com.example.gestionPedidos.orders.Exception.OrderLineException;
import com.example.gestionPedidos.orders.Exception.ProductException;
import com.example.gestionPedidos.orders.Model.Order;
import com.example.gestionPedidos.orders.Model.OrderLine;
import com.example.gestionPedidos.orders.Model.OrderStatus;
import com.example.gestionPedidos.orders.Model.Product;
import com.example.gestionPedidos.orders.Repository.OrderLineRepository;
import com.example.gestionPedidos.orders.Repository.OrderRepository;
import com.example.gestionPedidos.orders.Repository.ProductRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")

/**
 * @author chrquin
 * @author mariher
 */

public class orderController {

    private final OrderRepository repository;
    private final OrderLineRepository repositoryOL;
    private final ProductRepository productRepository;

    /**
     * Constructor de la API REST de pedidos.
     * 
     * @param repository        repositorio para pedidos.
     * @param repositoryOL      repositorio para líneas de pedido.
     * @param productRepository repositorio para productos.
     */
    orderController(OrderRepository repository, OrderLineRepository repositoryOL, ProductRepository productRepository) {
        this.repository = repository;
        this.repositoryOL = repositoryOL;
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los pedidos registrados.
     * 
     * @return La lista de pedidos registrados.
     */
    @GetMapping()
    public Object getEveryPedido() {
        return repository.findAll();
    }

    /**
     * Obtiene el pedido con asignado al {@code id}.
     * 
     * @param id el id del pedido.
     * @return El pedido.
     */
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            return repository.findPedidoById(id);
        } else {
            throw new OrderException("No hay un pedido con ese id.");
        }
    }

    /**
     * Crea un nuevo pedido.
     * 
     * @param nuevaOrder la información del nuevo pedido.
     * @return Un mensaje de confirmación.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String agregarOrder(@RequestBody Order nuevaOrder) {

        LocalDate deliveryDate = LocalDate.now().plusDays(7);
        Date newDate = Date.valueOf(deliveryDate);
        nuevaOrder.setExpectedDeliveryDate(newDate);

        try {
            repository.save(nuevaOrder);
        } catch (Exception e) {
            throw new OrderException(e.getMessage());
        }
        return "El pedido ha sido creado.";
    }

    /**
     * Modifica el pedido asignado a {@code id}
     * 
     * @param id            el identificador del pedido.
     * @param modifiedOrder los datos del pedido modificado.
     * @return Un mensaje de confirmación.
     */
    @PutMapping("/{id}")
    public String modificarOrderById(@PathVariable Integer id, @RequestBody Order modifiedOrder) {
        if (repository.existsById(id)) {
            repository.save(modifiedOrder);
            return "El pedido " + modifiedOrder.getId() + " fue modificado.";
        } else {
            throw new OrderException("No hay pedidos con ese id.");
        }
    }

    /**
     * Crea una línea de pedido.
     * 
     * @param nuevaOrderLine los datos de la nueva línea de pedido.
     * @return Un mensaje de confirmación.
     */
    @PostMapping(value = "/orderLine", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String agregarOrderLine(@RequestBody OrderLine nuevaOrderLine) {
        try {
            repositoryOL.save(nuevaOrderLine);
        } catch (Exception e) {
            throw new OrderLineException(e.getMessage());
        }
        return "La linea de pedido ha sido creada.";
    }

    /**
     * Borra una línea de pedido.
     * 
     * @param id el identificador de la línea de pedido.
     * @return Un mensaje de confirmación.
     */
    @DeleteMapping(value = "/orderLine/{id}")
    public String borrarOrderLine(@PathVariable Integer id) {
        if (repositoryOL.existsById(id)) {
            repositoryOL.deleteById(id);
            return "La linea de pedido" + id + " ha sido borrada.";
        } else {
            throw new OrderLineException("No exixte una línea de pedido con ese id.");
        }
    }

    /**
     * Borra el pedido asignado a {@code id}
     * 
     * @param id el id del pedido que se borrará.
     * @return Un mensaje de confirmación.
     */
    @DeleteMapping("/{id}")
    public String borrarOrderById(@PathVariable Integer id) {
        if (repository.existsById(id) && repository.findPedidoById(id)
                .getStatus().equals(OrderStatus.ORDERED)) {
            repository.deleteById(id);
            return "El pedido " + id + " fue borrado.";
        } else {
            throw new OrderException(
                    "No hay pedidos con ese id o el pedido que intenta borrar no está en estado ORDERED.");
        }
    }

    /**
     * Obtiene todos los productos registrados en el "almacén".
     * 
     * @return una lista con todos los productos registrados.
     */
    @GetMapping("/products")
    public Object getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Añade un nuevo producto.
     * 
     * @param newProduct los datos del nuevo producto que se agregará.
     * @return Un mensaje de confirmación.
     */
    @PostMapping("/products")
    public String addProduct(@RequestBody Product newProduct) {
        try {
            productRepository.save(newProduct);
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
        return "Se ha creado el producto";
    }
}
