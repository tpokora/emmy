package org.tpokora.users.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.auth.views.forms.UserForm;

public class UserTests {

    @Test
    public void testUserValueOfUserForm() {
        UserForm userForm = new UserForm();
        userForm.setUsername("testUser");
        userForm.setPassword("testPassword");
        userForm.setEmail("test@test.com");

        User user = User.valueOf(userForm);

        Assertions.assertEquals(userForm.getUsername(), user.getUsername());
        Assertions.assertEquals(userForm.getPassword(), user.getPassword());
        Assertions.assertEquals(userForm.getEmail(), user.getEmail());
    }

    @Test
    public void testUserValueOfUserFormIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            User.valueOf(null);
        });
    }

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
