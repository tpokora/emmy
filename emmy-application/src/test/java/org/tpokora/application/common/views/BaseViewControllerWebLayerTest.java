package org.tpokora.application.common.views;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
abstract public class BaseViewControllerWebLayerTest {

    @Autowired
    protected MockMvc mockMvc;
}
