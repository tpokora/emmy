package org.tpokora.console.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.tpokora.console.web.ConsoleViewConstants.*;

@Controller
public class ConsoleViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleViewController.class);

    @GetMapping(value = CONSOLE_VIEW_URL, name = CONSOLE_VIEW)
    public String home(Model model) {
        LOGGER.info(">> Console");
        return CONSOLE_VIEW_TEMPLATE;
    }
}
