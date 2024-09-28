package com.combatcritters.critterspring;

import com.internetEnemies.combatCritters.application.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
		//!!! THE FOLLOWING CODE IS TEMPORARY AND WILL BE CHANGED SEE CombatCritters#51 FOR DETAILS !!!
		final File DB_SRC = ResourceUtils.getFile("classpath:DBInit.script");
		final File target = File.createTempFile("temp-db", ".script");
		Files.copy(DB_SRC.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
		//!!! END TEMP !!!
		SpringApplication.run(CritterSpringApplication.class, args);
	}
	
	@GetMapping("/ping")
	public String ping(Principal principal, @RequestParam(value = "reply", defaultValue = "pong") String reply) {
		return reply + "\n" + principal.getName();
	}

}
