package com.combatcritters.critterspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


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

	public static void main(String[] args) {
		SpringApplication.run(CritterSpringApplication.class, args);
	}

}
