package org.tpokora.persistance.entity.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void testUserToString() {
        int id = 1;
        String testUser = "testUser";
        String testEmail = "testEmail@email.com";
        String testPassword = "testPassword";

        String expectedString = String.format("User{id=%d, username='%s', password='%s', email='%s', roles=null}",
                id, testUser, testPassword, testEmail);

        User user = new User(testUser, testPassword, testEmail);
        user.setId(id);

        Assertions.assertEquals(expectedString, user.toString());
    }

    @Test
    public void testRoleToString() {
        int id = 1;
        String testName = "testRole";
        Role role = new Role(testName);
        role.setId(id);

        String expectedString = String.format("Role{id=%d, name='%s'}", id, testName);

        Assertions.assertEquals(expectedString, role.toString());
    }
}
