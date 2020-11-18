package org.tpokora.application.common;

import org.junit.jupiter.api.Test;
import org.tpokora.application.common.services.PasswordEncoderGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordEncoderGeneratorTest {

    @Test
    void testGenerateHash() {
        String password = "123456";
        String hashedPassword = PasswordEncoderGenerator.passwordEncoder(password);
        assertEquals(60, hashedPassword.length());
    }
}
