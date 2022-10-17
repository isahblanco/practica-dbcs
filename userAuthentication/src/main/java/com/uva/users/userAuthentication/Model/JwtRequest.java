package com.uva.users.userAuthentication.Model;

import java.io.Serializable;

/**
 * @author chrquin
 * @author mariher
 */

public class JwtRequest implements Serializable{
    
    private static final long serialVersionUID = 5926468583005150707L;
	
	private String email;
	private String password;

    public JwtRequest()
	{
	}

	/**
	 * Constructor de una peticion jwt
	 * @param email email del usuario
	 * @param password password del usuario
	 */
    public JwtRequest(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

	/**
	 * Devuelve el email
	 * @return el email
	 */
    public String getEmail() {
		return this.email;
	}

	/**
	 * Establece el email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email= email;
	}

	/**
	 * Devuelve la contraseña
	 * @return la contraseña
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Establece la contraseña
	 * @param password la contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
