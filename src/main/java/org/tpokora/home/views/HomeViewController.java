package org.tpokora.home.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.tpokora.common.CommonConstants.SLASH;
import static org.tpokora.home.views.HomeViewConstants.*;

@Controller
public class HomeViewController {

    @GetMapping(value = { SLASH, HOME_VIEW_URL }, name = HOME_VIEW)
    public String home(Model model) {
        return HOME_VIEW;
    }

}
