package org.tpokora.common.utils;

import java.util.Objects;

public class StringUtils {

    public static final String STRING_IS_NULL = "String is null!";

    public static String makeFirstLetterUpperCase(String string) {
        Objects.requireNonNull(string, STRING_IS_NULL);
        if (string.length() <= 1) {
            return string.toUpperCase();
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
