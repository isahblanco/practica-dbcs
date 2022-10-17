package com.uva.users.userAuthentication.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author chrquin
 * @author mariher
 */

@Component
public class JwtTokenProvider implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.kid}")
    private String kid;

    /**
     * Obtiene el email del token
     * @param token token del que obtener el email
     * @return email del token
     */
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Obtiene la fecha de expiracion del token
     * @param token token
     * @return la fecha de expiracion
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Obtiene el claim del token
     * @param token token
     * @param claimsResolver funcion que obtiene el claim
     * @return claim del token
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene todos los claims del token haciendo uso de la firma
     * con la clave secreta
     * @param token tken
     * @return claims del token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Comprueba si el token ha expirado
     * @param token token
     * @return true si lo está, false si no
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Genera un token con los detalles del usuario
     * @param userDetails detalles del usuario
     * @return el token con los datos del usuario
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Genera el token definiendo su informacion(claims), firmandolo con HS512
     * y la firma con la clave secreta y compactandolo
     * @param claims informacion del usuario a ir en el token
     * @param subject contenido
     * @return el token generado
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .setHeaderParam("kid", kid)
                .compact();
    }

    /**
     * Valida el token parseandolo con la firma de la clave
     * secreta y parseando los claims
     * @param token token
     * @return true si es valido, false si no
     */
    public Boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado.");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado.");
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio.");
        }
        return false;
    }
}