package org.tpokora.login.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.tpokora.home.views.HomeViewConstants.*;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW_URL;

@Controller
public class LoginViewController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = LOGIN_VIEW_URL, name = LOGIN_VIEW)
    public String login(Model model) {
        return LOGIN_VIEW;
    }

    @GetMapping(value = SIGNIN_VIEW_URL, name = SIGNIN_VIEW)
    public String signin(Model model) {
        model.addAttribute("user", new User());
        return SIGNIN_VIEW;
    }

    @PostMapping(value = "add-user")
    public String addUser(@ModelAttribute User user) {
        User newUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
        newUser = userDetailsService.saveUser(newUser, "USER");
        UserDetails newUserDetails = new UserDetailsImpl(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUserDetails, null, newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return HOME_VIEW;
    }
}
