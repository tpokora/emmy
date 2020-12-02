package org.tpokora.application.rates.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.application.rates.services.api.IRatesAPIService;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.services.rates.RatesDaoService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatesService implements IRatesService {

    public final Logger LOGGER = LoggerFactory.getLogger(RatesService.class);

    public final int SAVE_INTERVAL = 4;

    private IRatesAPIService ratesAPIService;
    private RatesDaoService ratesDaoService;

    public RatesService(IRatesAPIService ratesAPIService, RatesDaoService ratesDaoService) {
        this.ratesAPIService = ratesAPIService;
        this.ratesDaoService = ratesDaoService;
    }

    @Override
    public List<RateEntity> findArchivedRateForDate(String from, String to, LocalDateTime localDateTime) {
        LOGGER.info(String.format(">>> Find Rates %s => %s for date: %s", from, to, DateUtils.parseDateToString(localDateTime)));
        return ratesDaoService.getRatesForDate(from, to, localDateTime);
    }

    @Override
    public List<RateEntity> findArchivedRates(String from, String to, LocalDateTime startDate, LocalDateTime endDate) {
        LOGGER.info(String.format(">>> Find Rates %s => %s between %s and %s", from, to, DateUtils.parseDateToString(startDate), DateUtils.parseDateToString(endDate)));
        return ratesDaoService.getRatesBetweenDates(from, to, startDate, endDate);
    }

    @Override
    public RateEntity saveRate(RateEntity rateEntity) {
        LOGGER.info(String.format(">>> Save rate entity: %s => %s, %s, %s", rateEntity.getFrom(), rateEntity.getTo(),
                rateEntity.getValue(), DateUtils.parseDateToString(rateEntity.getTimestamp())));
        if (checkIfRateExistsForInterval(rateEntity)) {
            return ratesDaoService.saveRate(rateEntity);
        }
        return rateEntity;
    }

    private boolean checkIfRateExistsForInterval(RateEntity rateEntity) {
        LocalDateTime startDate = rateEntity.getTimestamp().minusHours(SAVE_INTERVAL);
        if (ratesDaoService.getRatesBetweenDates(rateEntity.getFrom(),
                rateEntity.getTo(), startDate, rateEntity.getTimestamp()).isEmpty()) {
            LOGGER.info(">>> Rate exist for interval");
            return true;
        }
        return false;
    }
}
