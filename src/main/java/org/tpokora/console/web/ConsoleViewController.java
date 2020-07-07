package org.tpokora.console.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.users.model.UserDetailsImpl;

import static org.tpokora.console.web.ConsoleViewConstants.*;

@Controller
public class ConsoleViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleViewController.class);

    private UserDetailsImpl userDetails;

    @GetMapping(value = CONSOLE_VIEW_URL, name = CONSOLE_VIEW)
    public String home(Model model) {
        LOGGER.info(">> Console");
        getUserDetails();
        return CONSOLE_VIEW_TEMPLATE;
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetailsImpl)principal);
        }
    }
}
