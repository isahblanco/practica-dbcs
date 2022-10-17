package com.uva.users.userAuthentication.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uva.users.userAuthentication.Service.JwtUserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author chrquin
 * @author mariher
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired 
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getToken(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                
                String email = jwtTokenProvider.getEmailFromToken(token);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Fallo en el metodo doFilterInternal.");
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Devuelve el token de la peticion quitando el
     * Bearer de la cabecera
     * @param request peticion http
     * @return el token sin Bearer en la cabecera, null en caso
     * de que la cabecera no existiera o de primeras no lo tuviera
     */
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
