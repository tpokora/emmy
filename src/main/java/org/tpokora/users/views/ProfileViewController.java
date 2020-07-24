package org.tpokora.users.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tpokora.common.services.PasswordEncoderGenerator;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.users.views.forms.ModifyEmailForm;
import org.tpokora.users.views.forms.ModifyPasswordForm;
import org.tpokora.users.views.forms.ModifyUsernameForm;

import javax.validation.Valid;

import java.util.LinkedList;

import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW_URL;
import static org.tpokora.users.views.UsersViewConstants.*;

@Controller
public class ProfileViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProfileViewController.class);

    private UserDetailsImpl userDetails;

    private UserDetailsServiceImpl userDetailsService;

    public ProfileViewController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = PROFILE_VIEW_URL, name = "profile")
    public String myProfile(Model model) {
        LOGGER.info(">> Profile Page");
        getUserDetails();
        if (checkIfLogged()) return HOME_VIEW_URL;

        initializeViewModel(model);
        model.addAttribute("error", null);
        return PROFILE_VIEW_TEMPLATE;
    }

    @PostMapping(value = PROFILE_CHANGE_USERNAME, name = "changeUsername")
    public String changeUsername(Model model, @Valid ModifyUsernameForm modifyUsernameForm, BindingResult bindingResult) {
        LOGGER.info(">> Profile Page, changing username...");
        getUserDetails();
        if (checkIfLogged()) return HOME_VIEW_URL;

        if (bindingResult.hasErrors()) {
            initializeViewModel(model);
            addFormError(model, bindingResult);
            return PROFILE_VIEW_TEMPLATE;
        }

        if (userDetailsService.loadUserByUsername(modifyUsernameForm.getUsername()) != null) {
            initializeViewModel(model);
            model.addAttribute("error", "Username already exists!");
            return PROFILE_VIEW_TEMPLATE;
        }
        try {
            userDetailsService.updateUsername(userDetails.getId(), modifyUsernameForm.getUsername());
        } catch (Exception e) {
            LOGGER.info(">> Error changing username: {}", e.getLocalizedMessage());
            initializeViewModel(model);
            return PROFILE_VIEW_TEMPLATE;
        }
        relogUser(modifyUsernameForm.getUsername());
        return "redirect:/" + PROFILE_VIEW_TEMPLATE;
    }

    @PostMapping(value = PROFILE_CHANGE_EMAIL, name = "changeEmail")
    public String changeEmail(Model model, @Valid ModifyEmailForm modifyEmailForm, BindingResult bindingResult) {
        LOGGER.info(">> Profile Page, changing email...");
        getUserDetails();
        if (checkIfLogged()) return HOME_VIEW_URL;

        if (bindingResult.hasErrors()) {
            initializeViewModel(model);
            addFormError(model, bindingResult);
            return PROFILE_VIEW_TEMPLATE;
        }

        if (userDetailsService.loadUserByEmail(modifyEmailForm.getEmail()) != null) {
            initializeViewModel(model);
            model.addAttribute("error", "Email already exists!");
            return PROFILE_VIEW_TEMPLATE;
        }
        try {
            userDetailsService.updateEmail(userDetails.getId(), modifyEmailForm.getEmail());
        } catch (Exception e) {
            LOGGER.info(">> Error changing email: {}", e.getLocalizedMessage());
            initializeViewModel(model);
            return PROFILE_VIEW_TEMPLATE;
        }
        relogUser(userDetails.getUsername());
        return "redirect:/" + PROFILE_VIEW_TEMPLATE;
    }

    @PostMapping(value = PROFILE_CHANGE_PASSWORD, name = "changePassword")
    public String changePassword(Model model, @Valid ModifyPasswordForm modifyPasswordForm, BindingResult bindingResult) {
        LOGGER.info(">> Profile Page, changing password...");
        getUserDetails();
        if (checkIfLogged()) return HOME_VIEW_URL;

        if (bindingResult.hasErrors()) {
            initializeViewModel(model);
            addFormError(model, bindingResult);
            return PROFILE_VIEW_TEMPLATE;
        }

        try {
            userDetailsService.updatePassword(userDetails.getId(), PasswordEncoderGenerator.passwordEncoder(modifyPasswordForm.getPassword()));
        } catch (Exception e) {
            LOGGER.info(">> Error changing password: {}", e.getLocalizedMessage());
            initializeViewModel(model);
            return PROFILE_VIEW_TEMPLATE;
        }
        relogUser(userDetails.getUsername());
        return "redirect:/" + PROFILE_VIEW_TEMPLATE;
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetailsImpl)principal);
        }
    }

    private boolean checkIfLogged() {
        return userDetails == null;
    }

    public void relogUser(String username) {
        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void initializeViewModel(Model model) {
        model.addAttribute("user", userDetails);
        initializeForms(model);
    }

    private void initializeForms(Model model) {
        model.addAttribute("modifyUsernameForm", new ModifyUsernameForm());
        model.addAttribute("modifyEmailForm", new ModifyEmailForm());
        model.addAttribute("modifyPasswordForm", new ModifyPasswordForm());
    }

    private void addFormError(Model model, BindingResult bindingResult) {
        bindingResult.getAllErrors().stream().findFirst().ifPresent(objectError -> addError(model, objectError.getDefaultMessage()));
    }

    private void addError(Model model, String error) {
        model.addAttribute("error", error);
    }
}
