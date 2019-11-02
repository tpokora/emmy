package org.tpokora.users.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersViewController {

    @GetMapping("/users")
    public String users(Model model) {
        return "users";
    }
}
