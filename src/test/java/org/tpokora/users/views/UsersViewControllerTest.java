package org.tpokora.users.views;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tpokora.common.views.BaseViewTest;

public class UsersViewControllerTest extends BaseViewTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UsersViewController()).build();
    }

    // TODO: Write tests
    @Test
    public void testUsersPage() {
        Assert.assertTrue(true);
    }
}
