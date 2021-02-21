package org.tpokora.application.rates.views.forms;

import org.tpokora.application.common.views.forms.BasicForm;

import javax.validation.constraints.NotBlank;

public class RateForm extends BasicForm {

    @NotBlank(message = FIELD_REQUIRED)
    private String from;
    @NotBlank(message = FIELD_REQUIRED)
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
