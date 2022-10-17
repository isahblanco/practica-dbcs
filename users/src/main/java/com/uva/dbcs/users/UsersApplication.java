package com.uva.dbcs.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chrquin
 * @author mariher
 */

@SpringBootApplication
@RestController
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		System.out.println("Gestion");
		return "Hola desde Spring Boot gestion.";
	}
}
