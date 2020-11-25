package org.tpokora.persistance.entity.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.persistance.entity.weather.MonitoredCoordinatesEntity;

import java.util.Set;

public class UserTests {

    @Test
    public void testUserToString() {
        int id = 1;
        String testUserName = "testUserName";
        String testEmail = "testEmail@email.com";
        String testPassword = "testPassword";

        String expectedString = String.format("User{id=%d, username='%s', password='%s', email='%s', roles=null}",
                id, testUserName, testPassword, testEmail);

        User user = new User(testUserName, testPassword, testEmail);
        user.setId(id);

        Assertions.assertEquals(expectedString, user.toString());

        User testUser = new User(user);
        expectedString = String.format("User{id=%d, username='%s', password='%s', email='%s', roles=null}",
                testUser.getId(), testUser.getUsername(), testUser.getPassword(), testUser.getEmail());

        Assertions.assertEquals(expectedString, testUser.toString());

        String newTestUserName = "newTestUserName";
        String newPassword = "newPassword";
        String email = "newEmail@email.com";
        testUser.setUsername(newTestUserName);
        testUser.setPassword(newPassword);
        testUser.setEmail(email);

        expectedString = String.format("User{id=%d, username='%s', password='%s', email='%s', roles=null}",
                testUser.getId(), testUser.getUsername(), testUser.getPassword(), testUser.getEmail());

        Assertions.assertEquals(expectedString, testUser.toString());

        testUser.setMonitoredCoordinateEntities(Set.of(new MonitoredCoordinatesEntity()));
        testUser.setRoles(Set.of(new Role()));
        
        Assertions.assertFalse(testUser.getMonitoredCoordinateEntities().isEmpty());
        Assertions.assertFalse(testUser.getRoles().isEmpty());
    }

}
