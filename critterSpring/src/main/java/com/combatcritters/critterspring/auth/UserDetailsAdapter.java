package com.combatcritters.critterspring.auth;

import com.internetEnemies.combatCritters.Logic.exceptions.UserNotFoundException;
import com.internetEnemies.combatCritters.Logic.users.IUserManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * UserDetailsAdapter.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/26/24
 * 
 * @PURPOSE:    adapt IUserManager to spring UserDetailsService functionality
 */
public class UserDetailsAdapter implements UserDetailsService {
    
    private final IUserManager userManager;
    public UserDetailsAdapter(IUserManager userManager) {
        this.userManager = userManager;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.internetEnemies.combatCritters.objects.User user;
        try {
            user = userManager.getUserByUsername(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        Set<GrantedAuthority> authorities = new HashSet<>();//users all have the role 'USER'
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
