package com.combatcritters.critterspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class CritterSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CritterSpringApplication.class, args);
	}
	
	@GetMapping("/ping")
	public String ping(Principal principal, @RequestParam(value = "reply", defaultValue = "pong") String reply) {
		return reply + "\n" + principal.getName();
	}

}
