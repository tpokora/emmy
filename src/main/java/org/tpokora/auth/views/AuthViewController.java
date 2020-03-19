package org.tpokora.auth.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tpokora.auth.services.AuthService;
import org.tpokora.auth.views.forms.UserForm;
import org.tpokora.users.model.User;

import javax.validation.Valid;

import static org.tpokora.auth.AuthConstatns.*;

@Controller
public class AuthViewController {

    @Autowired
    private AuthService authService;

    @GetMapping(value = LOGIN_VIEW_URL, name = LOGIN_VIEW)
    public String login(Model model) {
        return LOGIN_VIEW_TEMPLATE;
    }

    @GetMapping(value = SIGNIN_VIEW_URL, name = SIGNIN_VIEW)
    public String signin(Model model) {
        return authService.signinView(model);
    }

    @PostMapping(value = "add-user")
    public String addUser(@Valid UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> addFormError(model, error));
            return authService.signinView(model);
        }
        return authService.registerNewUserView(User.valueOf(userForm));
    }

    private void addFormError(Model model, ObjectError error) {
        error.getDefaultMessage();
        model.addAttribute(error.getCodes()[1].substring(5) + "Error", error.getDefaultMessage());
    }
}
