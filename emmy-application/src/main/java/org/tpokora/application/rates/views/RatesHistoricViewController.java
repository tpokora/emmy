package org.tpokora.application.rates.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.tpokora.application.rates.views.forms.RateForm;
import org.tpokora.application.weather.views.DatesFormatterViewsHelper;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.services.rates.IRatesDaoService;

import javax.validation.Valid;
import java.util.List;

import static org.tpokora.application.rates.views.RatesViewConstants.*;

@Controller
public class RatesHistoricViewController {

    public static final String DATES_FORMATTER = "datesFormatter";
    public static final String RATE_FORM = "rateForm";
    public static final String RATES = "rates";

    private final Logger LOGGER = LoggerFactory.getLogger(RatesHistoricViewController.class);

    private IRatesDaoService ratesDaoService;

    public RatesHistoricViewController(@Qualifier("ratesDaoJpaService") IRatesDaoService ratesDaoService) {
        this.ratesDaoService = ratesDaoService;
    }

    @GetMapping(value = HISTORIC_RATES, name = RATES_HISTORIC_VIEW_NAME)
    public String historicRatesHome(Model model) {
        LOGGER.info(">>> RatesViewController");
        initializeModel(model);
        return HISTORIC_RATES_TEMPLATE_URL;
    }

    @GetMapping(value = HISTORIC_RATES_FIND, name = RATES_HISTORIC_VIEW_NAME)
    public String historicRates(@Valid RateForm rateForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return HISTORIC_RATES_TEMPLATE_URL;
        }
        LOGGER.info(">>> RatesViewController find Historic dates of rates from: {} to: {}", rateForm.getFrom(), rateForm.getTo());
        initializeModel(model);
        List<RateEntity> ratesDaysBeforeToday = ratesDaoService.getRatesDaysBeforeToday(rateForm.getFrom(), rateForm.getTo(), 30);
        model.addAttribute(DATES_FORMATTER, new DatesFormatterViewsHelper());
        model.addAttribute(RATES, ratesDaysBeforeToday);
        return HISTORIC_RATES_TEMPLATE_URL;
    }

    private void initializeModel(Model model) {
        model.addAttribute(DATES_FORMATTER, new DatesFormatterViewsHelper());
        model.addAttribute(RATE_FORM, new RateForm());
    }
}
