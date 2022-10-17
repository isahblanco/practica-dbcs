package com.uva.users.userAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chrquin
 * @author mariher
 */

@SpringBootApplication
public class UserAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		System.out.println("Autenticar");
		return "Hola desde Spring Boot autenticar.";
	}

}
