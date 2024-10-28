package com.combatcritters.critterspring.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * CORSConfig.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/6/24
 * 
 * @PURPOSE:    set cors config
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Value("${ORIGIN.url:http://localhost:3000}")
    private String origin;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH");
    }
}
