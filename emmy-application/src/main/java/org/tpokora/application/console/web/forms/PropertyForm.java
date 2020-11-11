package org.tpokora.application.console.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropertyForm {

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @NotNull
    @Size(min=1, max=30)
    private String value;

    @Size(max=200)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
