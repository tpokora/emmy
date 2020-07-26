package org.tpokora.users.views.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModifyUsernameForm {

    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
