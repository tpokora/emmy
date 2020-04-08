package org.tpokora.auth.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.auth.services.AuthViewService;
import org.tpokora.auth.views.forms.UserForm;
import org.tpokora.common.utils.StringUtils;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.views.forms.RoleForm;

import javax.validation.Valid;
import java.util.Objects;
import java.util.function.BiConsumer;

import static org.tpokora.auth.AuthConstatns.*;

@Controller
public class AuthViewController {

    public static final String USERNAME_ERROR = "usernameError";
    public static final String USER_ALREADY_EXISTS = "User already exists!";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
    public static final String EMAIL_ERROR = "emailError";
    public static final String ROLE_NAME_ERROR = "roleNameError";
    public static final String ROLE_NAME_ALREADY_EXISTS = "Role already exists!";

    @Autowired
    private AuthViewService authViewService;

    private BiConsumer<Model, ObjectError> addFormErrorConsumer = this::addFormError;

    @GetMapping(value = LOGIN_VIEW_URL, name = LOGIN_VIEW)
    public String login(Model model) {
        return LOGIN_VIEW_TEMPLATE;
    }

    @GetMapping(value = SIGNIN_VIEW_URL, name = SIGNIN_VIEW)
    public String signin(Model model) {
        return authViewService.signInView(model);
    }

    @PostMapping(value = "add-user")
    public String addUser(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> addFormErrorConsumer.accept(model, error));
            return authViewService.signInView(model);
        }
        if (checkForUser(userForm.getUsername(), model)) {
            return authViewService.signInView(model);
        }
        if (checkForEmail(userForm.getEmail(), model)) {
            return authViewService.signInView(model);
        }

        return authViewService.registerNewUserView(User.valueOf(userForm));
    }

    @PostMapping(value = "add-role")
    public String addUser(@Valid RoleForm roleForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> addFormErrorConsumer.accept(model, error));
            return authViewService.rolesView(model);
        }
        String roleNameUppercase = roleForm.getRoleName().toUpperCase();
        if (checkForRole(roleNameUppercase, model)) {
            return authViewService.rolesView(model);
        }
        roleForm.setRoleName(roleNameUppercase);
        Role newRole = Role.valueOf(roleForm);
        newRole = authViewService.createRole(newRole);

        return authViewService.rolesView(model);
    }

    private boolean checkForEmail(String email, Model model) {
        if (authViewService.checkIfEmailExists(email)) {
            addFormError(model, EMAIL_ERROR, EMAIL_ALREADY_EXISTS);
            return true;
        }

        return false;
    }

    private boolean checkForUser(String username, Model model) {
        if (authViewService.checkIfUserExists(username)) {
            addFormError(model, USERNAME_ERROR, USER_ALREADY_EXISTS);
            return true;
        }

        return false;
    }

    private boolean checkForRole(String roleName, Model model) {
        if (authViewService.checkIfRoleExists(roleName)) {
            addFormError(model, ROLE_NAME_ERROR, ROLE_NAME_ALREADY_EXISTS);
            return true;
        }

        return false;
    }

    private void addFormError(Model model, ObjectError error) {
        Objects.requireNonNull(error, "Error is null!");
        addFormError(model, formatErrorName(error.getCodes()[1].substring(5)), StringUtils.makeFirstLetterUpperCase(error.getDefaultMessage()));
    }

    private void addFormError(Model model, String errorName, String errorString) {
        Objects.requireNonNull(errorName, "Error name is null!");
        Objects.requireNonNull(errorString, "Error message is null!");
        model.addAttribute(errorName, errorString);
    }

    private String formatErrorName(String errorName) {
        return errorName + "Error";
    }
}
