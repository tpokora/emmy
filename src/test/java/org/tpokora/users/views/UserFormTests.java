package org.tpokora.users.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.auth.views.forms.UserForm;
import org.tpokora.users.views.forms.RoleForm;

public class UserFormTests {

    @Test
    public void testUserFormToString() {
        String testUser = "testUser";
        String testEmail = "testEmail@email.com";
        String testPassword = "testPassword";

        UserForm userForm = new UserForm();
        userForm.setUsername(testUser);
        userForm.setEmail(testEmail);
        userForm.setPassword(testPassword);

        String expectedString = String.format("UserForm{username='%s', password='%s', email='%s'}",
                testUser, testPassword, testEmail);
        Assertions.assertEquals(expectedString, userForm.toString());
    }

    @Test
    public void testRoleFormToString() {
        String testName = "testRole";

        RoleForm roleForm = new RoleForm(testName);

        String expectedString = String.format("RoleForm{name='%s'}", testName);
        Assertions.assertEquals(expectedString, roleForm.toString());
    }
}
