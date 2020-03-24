package org.tpokora.users.views.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleForm {

    @NotNull
    @Size(min=3, max=10)
    private String name;

    public RoleForm() { }

    public RoleForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("RoleForm{name='%s'}", this.name);
    }
}
