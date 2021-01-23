package org.tpokora.application.rates.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.tpokora.application.rates.views.RatesViewConstants.*;

@Controller(RATES)
public class RatesHistoricViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(RatesHistoricViewController.class);

    public RatesHistoricViewController() {

    }

    @GetMapping(value = HISTORIC_RATES, name = RATES_HISTORIC_VIEW_NAME)
    public String historicRatesHome(Model model) {
        LOGGER.info(">>> RatesViewController");
        return HISTORIC_RATES_TEMPLATE_URL;
    }
}
