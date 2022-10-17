package com.uva.dbcs.users.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue; 
import java.sql.Date;

import javax.persistence.Column;

/**
 * @author chrquin
 * @author mariher
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    private final Date createdAt = new Date(new java.util.Date().getTime());

    private Date updatedAt;

    /**
     * Constructor vacio de un usuario.
     */
    public User() {
    }

    //

    /**
     * Constructor de un usuario dados los correspondientes parámetros.
     * 
     * @param name      Nombre del usuario.
     * @param firstName Primer nombre del usuario.
     * @param lastName  Apellidos del usuario.
     * @param email     Correo electronico del usuario.
     * @param password  Contraseña del usuario.
     * @param enabled   True si está habilitado, false si no.
     * @param createdAt Fecha de creación del usuario.
     * @param updatedAt Fecha de úlitma modificación del usuario.
     */
    User(String name, String firstName, String lastName, String email, String password, boolean enabled, Date createdAt,
            Date updatedAt) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.updatedAt = new Date(new java.util.Date().getTime());
    }

    /**
     * Obtiene el id el usuario.
     * 
     * @return id del usuario.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return nombre del usuario.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtiene el primer nombre del usuario.
     * 
     * @return primer nombre del usuario.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return apellido del usuario.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Obtiene el email del usuario.
     * 
     * @return email del usuario.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return contraseña del usuario.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Obtiene el estado de habilitación del usuario.
     * 
     * @return true si habilitado, false si no.
     */
    public boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Obtiene la fecha de creación del usuario.
     * 
     * @return fecha de creación del usuario.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Obtiene la fecha de la última modificación del usuario.
     * 
     * @return fecha de la última modificación del usuario.
     */
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Establece el nombre del usuario dado un nombre.
     * 
     * @param name Nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece el primer nombre del usuario dado un primer nombre.
     * 
     * @param firstName Primer nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Establece el apellido del usuario dado un apellido.
     * 
     * @param lastName Apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Establece el email de usuario dado un email.
     * 
     * @param email Email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece la contraseña del usuario dada una contraseña.
     * 
     * @param password Contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece el estado de habilitación del usuario dado un boolean.
     * True habilitado, false si no.
     * 
     * @param enabled Nombre del usuario.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Establece la fecha de última modificación del usuario.
     * 
     * @param updatedAt Fecha de última modificación.
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = new Date(new java.util.Date().getTime());
    }
}
