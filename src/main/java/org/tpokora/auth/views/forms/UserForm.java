package org.tpokora.auth.views.forms;

import org.tpokora.users.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {

    @NotNull
    @Size(min=3, max=30)
    private String username;

    @NotNull
    @Size(min=3, max=30)
    private String password;

    @NotNull
    @Size(min=9, max=100)
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

    public String toString() {
        return "UserForm{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
