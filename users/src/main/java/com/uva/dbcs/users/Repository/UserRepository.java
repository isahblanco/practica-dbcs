package com.uva.dbcs.users.Repository;

import java.util.List;

import com.uva.dbcs.users.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chrquin
 * @author mariher
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Devuelve una lista con los usuarios inhabilitados.
     */
    List<User> findByEnabledFalse();

    /**
     * Devuelve una lista con los usuarios habilitados.
     */
    List<User> findByEnabledTrue();

    /**
     * Devuelve una lista con los usuarios correspondientes a los identificadores de
     * {@code ids}.
     * 
     * @param ids Una lista de identificadores.
     * @return Una lista de usuarios con los usuarios correspondientes a los ids.
     */
    List<User> findByIdIn(java.util.Collection<Integer> ids);

    boolean existsByEmail(String email);

    List<User> findByEmail(String email);

    User findUserByEmail(String email);

}
