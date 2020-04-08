package org.tpokora.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTests {

    @Test
    public void testStringFirstLetterUppercase() {
        String test = "test";
        String expectedString = "Test";

        Assertions.assertEquals(expectedString, StringUtils.makeFirstLetterUpperCase.apply(test));
    }

    @Test
    public void testStringFirstLetterUppercaseBlank() {
        String test = "";
        String expectedString = "";

        Assertions.assertEquals(expectedString, StringUtils.makeFirstLetterUpperCase.apply(test));
    }

    @Test
    public void testStringFirstLetterUppercaseOneLetter() {
        String test = "a";
        String expectedString = "A";

        Assertions.assertEquals(expectedString, StringUtils.makeFirstLetterUpperCase.apply(test));
    }

    @Test
    public void testStringFirstLetterUppercaseNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            StringUtils.makeFirstLetterUpperCase.apply(null);
        });
    }
}
