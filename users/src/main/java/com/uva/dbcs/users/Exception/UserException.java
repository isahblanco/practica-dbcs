package com.uva.dbcs.users.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)

/**
 * @author chrquin
 * @author mariher
 */
public class UserException extends RuntimeException {

    /**
     * Constructor de la excepcion.
     * @param mensaje Mensaje de la excepcion.
     */
    public UserException(String mensaje) {
        super(mensaje);
    }
}
