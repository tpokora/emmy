package org.tpokora.common.utils;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class StringUtils {

    public static final String STRING_IS_NULL = "String is null!";

    public static UnaryOperator<String> makeFirstLetterUpperCase = StringUtils::makeFirstLetterUpperCase;

    private static String makeFirstLetterUpperCase(String string) {
        Objects.requireNonNull(string, STRING_IS_NULL);
        if (string.length() <= 1) {
            return string.toUpperCase();
        }
        return string.substring(0, 1).toUpperCase().concat(string.substring(1));
    }
}
