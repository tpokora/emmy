package org.tpokora.users.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.tpokora.users.views.UsersViewConstants.PROFILE_VIEW_TEMPLATE;
import static org.tpokora.users.views.UsersViewConstants.PROFILE_VIEW_URL;

@Controller
public class UserViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersViewController.class);

    private UserDetailsImpl userDetails;

    private UserDetailsServiceImpl userDetailsService;

    public UserViewController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = PROFILE_VIEW_URL, name = "profile")
    public String myProfile(Model model) {
        LOGGER.info(">>> Profile Page");
        getUserDetails();
        model.addAttribute("user", userDetails);
        return PROFILE_VIEW_TEMPLATE;
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetailsImpl)principal);
        }
    }
}
