package org.tpokora.users.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.tpokora.users.views.UsersViewConstants.*;

@Controller
public class UsersViewController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = USERS_VIEW_URL, name = USERS_VIEW)
    public String users(Model model) {
        Optional<List<User>> allUsers = Optional.of(userDetailsService.getAllUsers());
        model.addAttribute("users", allUsers.get());
        return USERS_VIEW_TEMPLATE;
    }

    @Secured("ADMIN")
    @GetMapping(value = ROLES_VIEW_URL, name = ROLES_VIEW, params = "admin")
    public String roles(Model model) {
        Optional<List<Role>> allRoles = Optional.of(userDetailsService.getAllRoles());
        model.addAttribute("roles", allRoles.get());
        return  ROLES_VIEW_TEMPLATE;
    }
}
