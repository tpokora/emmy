package org.tpokora.users.views.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModifyPasswordForm {

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
