package org.tpokora.application.rates.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RatesHistoricViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(RatesHistoricViewController.class);

    public RatesHistoricViewController() {

    }

    @GetMapping(value = "/rates/historicRates", name = "historic-rates")
    public String historicRatesHome(Model model) {
        LOGGER.info(">>> RatesViewController");
        return "rates/historic_rates";
    }
}
