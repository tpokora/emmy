package org.tpokora.auth.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    private UserDetailsServiceImpl userDetailsService;

    public AuthService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetails createNewUser(User newUser) {
        newUser = new User(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
        newUser = userDetailsService.saveUser(newUser, "USER");
        return new UserDetailsImpl(newUser);
    }

    public boolean checkIfUserExists(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails != null;
    }

    public boolean checkIfEmailExists(String email) {
        UserDetails userDetails = userDetailsService.loadUserByEmail(email);
        return userDetails != null;
    }

    public boolean checkIfRoleExists(String roleName) {
        Role role = userDetailsService.getRole(roleName);
        return role != null;
    }

    public Role createRole(Role role) {
        return userDetailsService.createRole(role);
    }
}
