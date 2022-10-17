package com.uva.users.userAuthentication.Controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.uva.users.userAuthentication.Config.JwtTokenProvider;
import com.uva.users.userAuthentication.Model.JwtRequest;
import com.uva.users.userAuthentication.Model.JwtResponse;

/**
 * @author chrquin
 * @author mariher
 */

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")

public class UserAuthController {


    @Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

    /**
     * Autentifica al usuario en caso de que sus datos se encuentre en la base de 
     * datos y devuelve el token correspondiente para su inicio de sesion
     * @param loginData datos de login, email y contraseña
     * @return el token generado de la peticion
     * @throws Exception en caso de que la autenticacion no haya sido existosa
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generarTokenAutenticacion(@RequestBody JwtRequest loginData) throws Exception {

        authenticate(loginData.getEmail(), loginData.getPassword());
            
        final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(loginData.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        Objects.requireNonNull(email);
		Objects.requireNonNull(password);
        try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
    }
}
