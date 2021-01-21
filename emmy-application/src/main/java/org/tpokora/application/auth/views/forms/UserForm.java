package org.tpokora.application.auth.views.forms;

import org.tpokora.application.common.views.forms.BasicForm;
import org.tpokora.persistance.entity.users.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm extends BasicForm {

    @NotNull(message = FIELD_REQUIRED)
    @Size(min=3, max=30, message = MIN_AND_MAX)
    private String username;

    @NotNull(message = FIELD_REQUIRED)
    @Size(min=3, max=30, message = MIN_AND_MAX)
    private String password;

    @NotNull(message = FIELD_REQUIRED)
    @Size(min=9, max=100, message = MIN_AND_MAX)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User valueOf() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
