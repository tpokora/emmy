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
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return true;
        } catch (NoSuchElementException | UsernameNotFoundException e) {
            return false;
        }
    }

    public boolean checkIfEmailExists(String email) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByEmail(email);
            return true;
        } catch (NoSuchElementException | UsernameNotFoundException e) {
            return false;
        }
    }

    public boolean checkIfRoleExists(String roleName) {
        try {
            Role role = userDetailsService.getRole(roleName);
            return true;
        } catch (NoSuchElementException | EntityNotFoundException e) {
            return false;
        }
    }

    public Role createRole(Role role) {
        return userDetailsService.createRole(role);
    }
}
