package org.tpokora.application.console.web.forms;

import org.tpokora.application.common.views.forms.BasicForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropertyForm extends BasicForm {

    public static final String NAME = "Name ";
    public static final String VALUE = "Value ";
    public static final String DESCRIPTION = "Description ";

    @NotNull(message = FIELD_REQUIRED)
    @Size(min=3, max=30, message = NAME + MIN_AND_MAX)
    private String name;

    @NotNull(message = FIELD_REQUIRED)
    @Size(min=1, max=30, message = VALUE + MIN_AND_MAX)
    private String value;

    @Size(max=200, message = DESCRIPTION + MAX)
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
