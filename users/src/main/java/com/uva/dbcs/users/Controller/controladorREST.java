package com.uva.dbcs.users.Controller;

import java.util.List;

import com.uva.dbcs.users.Exception.UserException;
import com.uva.dbcs.users.Model.User;
import com.uva.dbcs.users.Repository.UserRepository;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")

/**
 * @author chrquin
 * @author mariher
 */
public class controladorREST {

    private final UserRepository repository;

    /**
     * Constructor del controlador de la API REST
     * 
     * @param repository Repositorio REST de la clase User.
     */
    controladorREST(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Devuelve una lista de usuarios registrados. Dependiendo del atributo de la
     * URI devolverá una lista con los usuarios habilitados, una con los usuarios
     * inhabilitados, o bien una con todos los usuarios.
     * 
     * @return Una lista de los usuarios registrados aplicando las restricciones
     *         citadas anteriormente.
     */
    @GetMapping()
    public Object getAllUsers(@RequestParam(required = false) String enable) {

        Object lista;

        if (enable == null) {
            lista = repository.findAll();
        } else if (enable.equals("false")) {
            lista = repository.findByEnabledFalse();
        } else {
            lista = repository.findByEnabledTrue();
        }
        return lista;
    }

    /**
     * Devuelve el usuario correspondiente dado un email.
     */
    @GetMapping(value = "/email")
    public Object getUserbyEmail(@RequestParam(required = false) String email) {
        Object user = null;

        if (repository.existsByEmail(email)) {
            user = repository.findByEmail(email);
        }
        return user;
    }

    /**
     * Devuelve al usuario correspondiente al identificador {@code id}.
     * 
     * @param id El identificador del usuario que se quiere obtener.
     * @return El usuario correspondiente al identificador {@code id}.
     */
    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Integer id) {
        User usuario = repository.findById(id).orElseThrow(() -> new UserException("No hay usuarios con ese ID."));
        return usuario;
    }

    /**
     * Registra un nuevo usuario en el sistema y lo agrega a la base de datos.
     * 
     * @param nuevoUsuario El usuario que se agregará a la BD en forma de JSON.
     * @return Un mensaje informando que el usuario ha sido registrado.
     * @throws UserException si ocurre un fallo al agregar al usuario.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String nuevoUser(@RequestBody User nuevoUsuario) {
        try {
            String preCrypt = nuevoUsuario.getPassword();
            nuevoUsuario.setPassword(BCrypt.hashpw(preCrypt, BCrypt.gensalt()));
            repository.save(nuevoUsuario);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return "El usuario " + nuevoUsuario.getName() + " ha sido creado.";
    }

    /**
     * Modifica al usuario designado por {@code id} para cambiar el valor de sus
     * atributos.
     * 
     * @param id                El identificador del usuario a modificar.
     * @param usuarioModificado El usuario a modificar.
     * @throws UserException si el {@code id} no corresponde a ningún usuario
     *                       registrado.
     */
    @PutMapping("/{id}")
    public String modificarUserByID(@PathVariable Integer id, @RequestBody User usuarioModificado) {
        if (repository.existsById(id)) {
            repository.save(usuarioModificado);
            return "El usuario " + usuarioModificado.getName() + " con id: " + id + " ha sido modificado.";
        } else {
            throw new UserException("No hay usuarios con ese ID.");
        }
    }

    /**
     * Borra al usuario correspondiente al identificador {@code id}.
     * 
     * @param id El identificador del usuario que se desea borrar.
     * @return Un mensaje indicando que el usuario correspondiente al identificador.
     *         {@code id} ha sido borrado con éxito.
     * @throws UserException si el identificador no corresponde a ningún usuario
     *                       registrado.
     */
    @DeleteMapping("/{id}")
    public String borrarUserByID(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "El usuario con id: " + id + " fue borrado.";
        } else {
            throw new UserException("No hay usuarios con ese ID.");
        }
    }

    /**
     * Habilita a todos los usuarios correspondientes a los identificadores en la
     * lista {@code user_id}.
     * 
     * @return Un mensaje indicando al usuario que se han habilitado correctamente
     *         los uruarios dados.
     * @throws UserException si el id no se corresponde con el de ninguno de los
     *                       usuarios registrados.
     */
    @PutMapping("/enable")
    public String updateEnableUsers(@RequestParam List<Integer> user_id) {
        for (Integer id : user_id) {
            if (!repository.existsById(id)) {
                throw new UserException("No existe un usuario con el id dado.");
            }
            User usuario = repository.findById(id).get();
            usuario.setEnabled(true);
            repository.save(usuario);
        }
        return "Se ha habilitado correctamente a todos los usuarios";
    }

    /**
     * Devuelve el usuario correspondiente dado un email.
     */
    @GetMapping(value = "/email2")
    public Object getbyEmail(@RequestParam String email) {
        Object user = null;

        if (repository.existsByEmail(email)) {
            user = repository.findUserByEmail(email);
        }
        return user;
    }

    /**
     * Devuelve el id del usuario dado su email
     * @param email email del usuario
     * @return el id del usuario
     */
    @GetMapping(value = "/getId")
    public String getIdByEmail(@RequestParam String email) {
        return repository.findUserByEmail(email).getId().toString() ;
    }
}
