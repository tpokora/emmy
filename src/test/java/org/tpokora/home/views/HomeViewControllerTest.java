package org.tpokora.home.views;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tpokora.common.views.BaseViewTest;
import org.tpokora.users.dao.UserRepository;

public class HomeViewControllerTest extends BaseViewTest {

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeViewController()).build();
    }

    @Test
    public void testHomePage() {
        Assert.assertTrue(false);
    }

}
