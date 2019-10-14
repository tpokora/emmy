package org.tpokora.views.validators;

import com.vaadin.flow.data.validator.StringLengthValidator;

public class ConfigViewValidators {

    private final static int MIN_CHAR_LENGTH = 5;
    private final static int MAX_CHAR_LENGTH = 100;

    public final static StringLengthValidator valueFieldValidator() {
        return new StringLengthValidator(
                String.format("Value needs to be at least %d characters long", MIN_CHAR_LENGTH),
                MIN_CHAR_LENGTH,
                MAX_CHAR_LENGTH);
    }
}
