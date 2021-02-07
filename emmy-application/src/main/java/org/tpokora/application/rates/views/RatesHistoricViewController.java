package org.tpokora.application.rates.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.application.weather.views.DatesFormatterViewsHelper;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.services.rates.IRatesDaoService;

import java.util.List;

import static org.tpokora.application.rates.views.RatesViewConstants.*;

@Controller
public class RatesHistoricViewController {

    public static final String DATES_FORMATTER = "datesFormatter";

    private final Logger LOGGER = LoggerFactory.getLogger(RatesHistoricViewController.class);

    private IRatesDaoService ratesDaoService;

    public RatesHistoricViewController(@Qualifier("ratesDaoJpaService") IRatesDaoService ratesDaoService) {
        this.ratesDaoService = ratesDaoService;
    }

    @GetMapping(value = HISTORIC_RATES, name = RATES_HISTORIC_VIEW_NAME)
    public String historicRatesHome(Model model) {
        LOGGER.info(">>> RatesViewController");
        List<RateEntity> ratesDaysBeforeToday = ratesDaoService.getRatesDaysBeforeToday("XAU", "PLN", 30);
        model.addAttribute(DATES_FORMATTER, new DatesFormatterViewsHelper());
        model.addAttribute("rates", ratesDaysBeforeToday);
        return HISTORIC_RATES_TEMPLATE_URL;
    }
}
