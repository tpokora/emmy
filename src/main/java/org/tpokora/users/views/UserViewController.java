package org.tpokora.users.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW_URL;
import static org.tpokora.users.views.UsersViewConstants.*;

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
        LOGGER.info(">> Profile Page");
        getUserDetails();
        if (userDetails == null) {
            return HOME_VIEW_URL;
        }
        model.addAttribute("user", userDetails);
        model.addAttribute("modifyUserForm", new ModifyUserForm());
        model.addAttribute("error", null);
        return PROFILE_VIEW_TEMPLATE;
    }

    @PostMapping(value = PROFILE_CHANGE_USERNAME, name = "changeUsername")
    public String changeUsername(Model model, @ModelAttribute ModifyUserForm modifyUserForm) {
        LOGGER.info(">> Profile Page, changing username...");
        getUserDetails();
        if (userDetails == null) {
            return HOME_VIEW_URL;
        }
        if (userDetailsService.loadUserByUsername(modifyUserForm.getUsername()) != null) {
            model.addAttribute("user", userDetails);
            model.addAttribute("error", "Username already exists!");
            return PROFILE_VIEW_TEMPLATE;
        }
        try {
            userDetailsService.updateUsername(userDetails.getId(), modifyUserForm.getUsername());
        } catch (Exception e) {
            LOGGER.info(">> Error changing username: {}", e.getLocalizedMessage());
            return PROFILE_VIEW_TEMPLATE;
        }
        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(modifyUserForm.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/" + PROFILE_VIEW_TEMPLATE;
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetailsImpl)principal);
        }
    }

    public class ModifyUserForm {
        private String username;
        private String email;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
