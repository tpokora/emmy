package org.tpokora.application.auth.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tpokora.application.auth.views.forms.UserForm;
import org.tpokora.application.users.services.UserDetailsServiceImpl;
import org.tpokora.application.users.views.forms.RoleForm;
import org.tpokora.config.constants.AuthConstants;
import org.tpokora.persistance.entity.users.Role;
import org.tpokora.persistance.entity.users.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.tpokora.application.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.config.constants.UsersViewConstants.ROLES_VIEW_TEMPLATE;

@Service
public class AuthViewService {

    private static final String USER_FOR_LOGIN_IS_NULL = "User for login is null!";
    public static final String USER_FORM_ATTRIBUTE = "userForm";
    public static final String ROLES_ATTRIBUTE = "roles";
    public static final String ROLE_FORM_ATTRIBUTE = "roleForm";
    private UserDetailsServiceImpl userDetailsService;
    private AuthService authService;

    public AuthViewService(UserDetailsServiceImpl userDetailsService, AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.authService = authService;
    }

    public String signInView(Model model) {
        model.addAttribute(USER_FORM_ATTRIBUTE, new UserForm());
        return AuthConstants.SIGNIN_VIEW_TEMPLATE;
    }

    public String rolesView(Model model) {
        Optional<List<Role>> allRoles = Optional.of(userDetailsService.getAllRoles());
        model.addAttribute(ROLES_ATTRIBUTE, allRoles.get());
        model.addAttribute(ROLE_FORM_ATTRIBUTE, new RoleForm());
        return ROLES_VIEW_TEMPLATE;
    }

    public String registerNewUserView(User user) {
        var newUserDetails = this.authService.createNewUser(user);
        loginUser(newUserDetails);
        return HOME_VIEW;
    }

    private void loginUser(UserDetails userToLogin) {
        Objects.requireNonNull(userToLogin, USER_FOR_LOGIN_IS_NULL);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userToLogin, null, userToLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Role createRole(Role newRole) {
        return authService.createRole(newRole);
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
