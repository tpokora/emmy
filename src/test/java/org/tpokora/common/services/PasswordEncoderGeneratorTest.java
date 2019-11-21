package org.tpokora.common.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordEncoderGeneratorTest {

    @Test
    public void test_generateHash() {
        String password = "123456";
        String hashedPassword = PasswordEncoderGenerator.passwordEncoder(password);
        assertEquals(60, hashedPassword.length());
    }
}
