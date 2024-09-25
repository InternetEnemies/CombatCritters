package com.combatcritters.critterspring.auth;

import com.combatcritters.critterspring.auth.payloads.LoginPayload;
import com.combatcritters.critterspring.auth.payloads.RegisterPayload;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import com.internetEnemies.combatCritters.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IUserManager userManager;
    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IUserManager userManager) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginPayload payload){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.username(), payload.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterPayload payload){
        if (userManager.existsByUsername(payload.username())){ // check for conflicting user
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
        
        //create the new user
        User user = userManager.createUser(
                payload.username(), 
                passwordEncoder.encode(payload.password())
        );
        return new ResponseEntity<>("Registered new user:"+user.getUsername(), HttpStatus.CREATED);
    }
}
