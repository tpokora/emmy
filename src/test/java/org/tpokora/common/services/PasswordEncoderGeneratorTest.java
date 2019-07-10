package org.tpokora.common.services;

import org.junit.Assert;
import org.junit.Test;

public class PasswordEncoderGeneratorTest {

    @Test
    public void test_generateHash() {
        String password = "123456";
        String hashedPassword = PasswordEncoderGenerator.passwordEncoder(password);
        Assert.assertEquals(60, hashedPassword.length());
    }
}
