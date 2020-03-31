package org.tpokora.users.model;

public class UserTestUtils {

    public static User createTestUser(String userName, String userPassword, String userEmail) {
        User testUser = new User();
        testUser.setUsername(userName);
        testUser.setPassword(userPassword);
        testUser.setEmail(userEmail);
        return testUser;
    }
}
