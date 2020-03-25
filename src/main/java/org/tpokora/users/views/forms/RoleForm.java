package org.tpokora.users.views.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleForm {

    @NotNull
    @Size(min=3, max=10)
    private String roleName;

    public RoleForm() { }

    public RoleForm(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return String.format("RoleForm{roleName='%s'}", this.roleName);
    }
}
