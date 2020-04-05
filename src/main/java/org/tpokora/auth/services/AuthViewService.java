package org.tpokora.auth.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.users.views.forms.RoleForm;

import java.util.List;
import java.util.Optional;

import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.users.views.UsersViewConstants.ROLES_VIEW_TEMPLATE;

public class AuthViewService {

    private UserDetailsServiceImpl userDetailsService;
    private AuthService authService;

    public AuthViewService(UserDetailsServiceImpl userDetailsService, AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.authService = authService;
    }

    public String signInView(Model model) {
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
        var newUserDetails = this.authService.createNewUser(user);
        loginUser(newUserDetails);
        return HOME_VIEW;
    }

    private void loginUser(UserDetails userToLogin) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userToLogin, null, userToLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void createRole(Role newRole) {
        authService.createRole(newRole);
    }

    public boolean checkIfEmailExists(String email) {
        return authService.checkIfEmailExists(email);
    }

    public boolean checkIfUserExists(String username) {
        return authService.checkIfUserExists(username);
    }

    public boolean checkIfRoleExists(String roleName) {
        return authService.checkIfRoleExists(roleName);
    }
}
