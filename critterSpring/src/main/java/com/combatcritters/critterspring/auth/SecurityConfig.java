package com.combatcritters.critterspring.auth;

import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.data.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/26/24
 * 
 * @PURPOSE:    configure security/authentication for the api
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private static final String AUTH = "/users/auth/**";
    
    @Bean
    public IUserManager userManager(Database database) {
        return new UserManager(database.getUsersDB());
    }
    
    @Bean
    public UserDetailsService userDetailsService(IUserManager userManager) {
        return new UserDetailsAdapter(userManager);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.ignoringRequestMatchers("**"))//!! this disables csrf protection, if we add OAuth this needs to be changed !!
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS,"**").permitAll()
                        .requestMatchers(AUTH).permitAll()// permit access to auth related endpoints without authentication
                        .anyRequest().authenticated())// require all authentication on all other routes
                .build();
    }
    
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
