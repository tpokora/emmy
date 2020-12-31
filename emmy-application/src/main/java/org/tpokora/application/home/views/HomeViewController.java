package org.tpokora.application.home.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.tpokora.config.constants.CommonConstants.SLASH;
import static org.tpokora.application.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.application.home.views.HomeViewConstants.HOME_VIEW_URL;

@Controller
public class HomeViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeViewController.class);

    @GetMapping(value = { SLASH, HOME_VIEW_URL }, name = HOME_VIEW)
    public String home(Model model) {
        LOGGER.info(">> HomeView");
        return HOME_VIEW;
    }

}
