package org.tpokora.auth.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.auth.services.AuthService;
import org.tpokora.users.model.User;

import static org.tpokora.home.views.HomeViewConstants.*;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW_URL;

@Controller
public class AuthViewController {

    @Autowired
    private AuthService authService;

    @GetMapping(value = LOGIN_VIEW_URL, name = LOGIN_VIEW)
    public String login(Model model) {
        return LOGIN_VIEW;
    }

    @GetMapping(value = SIGNIN_VIEW_URL, name = SIGNIN_VIEW)
    public String signin(Model model) {
        return authService.signinView(model);
    }

    @PostMapping(value = "add-user")
    public String addUser(@ModelAttribute User user) {
        return authService.registerNewUserView(user);
    }
}
