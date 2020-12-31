package org.tpokora.persistance.entity.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class RoleTests {

    @Test
    public void testRoleToString() {
        int id = 1;
        String testRole = "Test Role";
        Role role = new Role(id, testRole);

        String expectedRoleString = String.format("Role{id=%d, name='%s'}", id, testRole);
        Assertions.assertEquals(expectedRoleString, role.toString());

        testRole = "secondTestRole";
        role = Role.valueOf(testRole);
        expectedRoleString = String.format("Role{id=%d, name='%s'}", 0, testRole);
        Assertions.assertEquals(expectedRoleString, role.toString());

        role = new Role(role);
        Assertions.assertEquals(expectedRoleString, role.toString());

        role = new Role();
        role.setId(1);
        role.setName(testRole);
        role.setUsers(Set.of(new User()));

        expectedRoleString = String.format("Role{id=%d, name='%s'}", role.getId(), role.getName());
        Assertions.assertEquals(expectedRoleString, role.toString());
        Assertions.assertFalse(role.getUsers().isEmpty());
    }
}
