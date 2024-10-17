package com.combatcritters.critterspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

/**
 * CritterSpringApplication.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/28/24
 * 
 * @PURPOSE:    Main entry point for the Spring application
 */
@SpringBootApplication
@RestController
public class CritterSpringApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CritterSpringApplication.class, args);
	}
	
	@GetMapping("/ping")
	public String ping(Principal principal, @RequestParam(value = "reply", defaultValue = "pong") String reply) {
		return reply + "\n" + principal.getName();
	}

}
