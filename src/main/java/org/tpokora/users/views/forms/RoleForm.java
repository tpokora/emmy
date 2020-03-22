package org.tpokora.users.views.forms;

public class RoleForm {

    private String name;

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
