package org.tpokora.application.users.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.application.users.services.UserDetailsServiceImpl;
import org.tpokora.application.users.views.forms.RoleForm;
import org.tpokora.persistance.entity.users.Role;
import org.tpokora.persistance.entity.users.User;

import java.util.List;
import java.util.Optional;

import static org.tpokora.config.constants.UsersViewConstants.*;

@Controller
public class UsersViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersViewController.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = USERS_VIEW_URL, name = USERS_VIEW)
    public String users(Model model) {
        LOGGER.info(">> UsersView");
        Optional<List<User>> allUsers = Optional.of(userDetailsService.getAllUsers());
        model.addAttribute("users", allUsers.get());
        return USERS_VIEW_TEMPLATE;
    }

    @Secured("ADMIN")
    @GetMapping(value = ROLES_VIEW_URL, name = ROLES_VIEW, params = "admin")
    public String roles(Model model) {
        LOGGER.info(">> RolesView");
        Optional<List<Role>> allRoles = Optional.of(userDetailsService.getAllRoles());
        model.addAttribute("roles", allRoles.get());
        model.addAttribute("roleForm", new RoleForm());
        return  ROLES_VIEW_TEMPLATE;
    }
}
