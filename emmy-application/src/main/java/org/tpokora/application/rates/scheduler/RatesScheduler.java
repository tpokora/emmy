package org.tpokora.application.rates.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tpokora.application.rates.services.RatesService;
import org.tpokora.application.weather.scheduler.MonitorCoordinatesScheduler;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class RatesScheduler {

    private final Logger LOGGER = LoggerFactory.getLogger(MonitorCoordinatesScheduler.class);

    private RatesService ratesService;

    public RatesScheduler(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @Scheduled(cron = "0 0 8,20 * * *")
    public void checkGoldRates() {
        LOGGER.info(">>> Check Gold Rates");
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        Optional<RateEntity> optionalRateEntity = ratesService.findRateForDate("XAU", "PLN", date);
        optionalRateEntity.ifPresent(rateEntity -> ratesService.saveRate(rateEntity));

    }
}
