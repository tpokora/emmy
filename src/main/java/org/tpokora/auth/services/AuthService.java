package org.tpokora.auth.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;

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

    private void loginUser(UserDetails userToLogin) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userToLogin, null, userToLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
