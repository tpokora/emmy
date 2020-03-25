package org.tpokora.auth.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.users.views.forms.RoleForm;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.users.views.UsersViewConstants.ROLES_VIEW_TEMPLATE;

@Service
public class AuthService {

    private UserDetailsServiceImpl userDetailsService;

    public AuthService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String signinView(Model model) {
        model.addAttribute("user", new User());
        return SIGNIN_VIEW_TEMPLATE;
    }

    public String rolesView(Model model) {
        Optional<List<Role>> allRoles = Optional.of(userDetailsService.getAllRoles());
        model.addAttribute("roles", allRoles.get());
        model.addAttribute("roleForm", new RoleForm());
        return ROLES_VIEW_TEMPLATE;
    }

    public String registerNewUserView(User user) {
        var newUserDetails = createNewUser(user);
        loginUser(newUserDetails);
        return HOME_VIEW;
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

    private void loginUser(UserDetails userToLogin) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userToLogin, null, userToLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
