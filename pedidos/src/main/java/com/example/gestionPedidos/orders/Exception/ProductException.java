package com.example.gestionPedidos.orders.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)

/**
 * @author chrquin
 * @author mariher
 */

public class ProductException extends RuntimeException {

    public ProductException(String mensaje) {
        super(mensaje);
    }
}