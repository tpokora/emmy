package org.tpokora.users.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.auth.views.forms.UserForm;

public class UserTests {

    @Test
    public void userValueOfUserForm() {
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
    public void userValueOfUserFormIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            User.valueOf(null);
        });
    }
}
