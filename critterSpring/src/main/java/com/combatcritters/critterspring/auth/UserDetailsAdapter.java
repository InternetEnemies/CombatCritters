package com.combatcritters.critterspring.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsAdapter implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("user")) {//we only have 1 user with details 'user' 'password'
            throw new UsernameNotFoundException("Invalid username");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();//users all have the role 'USER'
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User("user","password",authorities);
    }
}
