package org.tpokora.application.users.views.forms;

import org.tpokora.persistance.entity.users.Role;

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

    public Role valueOf() {
        return Role.valueOf(roleName);
    }

    public String toString() {
        return String.format("RoleForm{roleName='%s'}", this.roleName);
    }
}
