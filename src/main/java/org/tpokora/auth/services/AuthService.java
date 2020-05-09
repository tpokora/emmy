package org.tpokora.auth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import java.util.Objects;

@Service
public class AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private static final String USER_ROLE_NAME = "USER";
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetails createNewUser(User newUser) {
        Objects.requireNonNull(newUser, "User is null!");
        LOGGER.debug(String.format("Creating new user: %s, %s", newUser.getUsername(), newUser.getEmail()));
        newUser = new User(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
        newUser = userDetailsService.saveUser(newUser, USER_ROLE_NAME);
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
