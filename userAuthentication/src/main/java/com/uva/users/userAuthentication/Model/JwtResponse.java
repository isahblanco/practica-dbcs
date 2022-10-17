package com.uva.users.userAuthentication.Model;

import java.io.Serializable;

/**
 * @author chrquin
 * @author mariher
 */

public class JwtResponse implements Serializable{

    private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	/**
	 * Constructor de la respuesta jwt
	 * @param jwttoken token
	 */
    public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	/**
	 * Devuelve el token
	 * @return el token
	 */
	public String getToken() {
		return this.jwttoken;
	}
}
