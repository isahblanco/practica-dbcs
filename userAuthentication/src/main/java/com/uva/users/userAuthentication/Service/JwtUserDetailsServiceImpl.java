
package com.uva.users.userAuthentication.Service;


import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONObject;

/**
 * @author chrquin
 * @author mariher
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        String url = "http://GestorUsuarios-API:8080/users/email2?email="+email;
        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(jsonStr);
        String userEmail = jsonObject.getString("email");
        String userPasswd = jsonObject.getString("password");
        
        return new User (userEmail, userPasswd, new ArrayList<>());
    }
}
