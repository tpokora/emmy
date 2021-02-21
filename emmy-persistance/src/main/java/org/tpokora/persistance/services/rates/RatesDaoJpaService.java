package org.tpokora.persistance.services.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.repositories.rates.RatesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("ratesDaoJpaService")
public class RatesDaoJpaService implements IRatesDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(RatesDaoJpaService.class);

    private final RatesRepository ratesRepository;

    public RatesDaoJpaService(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public RateEntity saveRate(RateEntity rateEntity) {
        Objects.requireNonNull(rateEntity, "RateEntity can't be null!");
        LOGGER.info(">>> Saving Forecast to DB");
        return ratesRepository.save(rateEntity);
    }

    public List<RateEntity> getRatesBetweenDates(String from, String to, LocalDateTime startDate, LocalDateTime endDate) {
        Objects.requireNonNull(from, "'from' can't be null!");
        Objects.requireNonNull(to, "'to' can't be null!");
        Objects.requireNonNull(startDate, "startDate can't be null!");
        Objects.requireNonNull(endDate, "endDate can't be null!");
        LOGGER.info(String.format(">>> Get rates %s => %s between %s and %s", from, to, DateUtils.parseDateToString(startDate), DateUtils.parseDateToString(endDate)));
        return ratesRepository.findAllByFromContainsIgnoreCaseAndToContainsIgnoreCaseAndTimestampBetweenOrderByTimestampAsc(from, to, startDate, endDate);
    }

    public List<RateEntity> getRatesDaysBeforeToday(String from, String to, int minusDays) {
        Objects.requireNonNull(from, "'from' can't be null!");
        Objects.requireNonNull(to, "'to' can't be null!");
        LOGGER.info(String.format(">>> Get rates %s before today", minusDays));
        return ratesRepository.findAllByFromContainsIgnoreCaseAndToContainsIgnoreCaseAndTimestampBetweenOrderByTimestampAsc(from, to, LocalDateTime.now().minusDays(minusDays), LocalDateTime.now());
    }

    public List<RateEntity> getRatesForDate(String from, String to, LocalDateTime localDateTime) {
        Objects.requireNonNull(from, "'from' can't be null!");
        Objects.requireNonNull(to, "'to' can't be null!");
        Objects.requireNonNull(localDateTime, "'localDateTime' can't be null!");
        LOGGER.info(String.format(">>> Get rates %s from", DateUtils.parseDateToString(localDateTime)));
        LocalDateTime startLocalDateTime = localDateTime.toLocalDate().atTime(0,0, 0);
        LocalDateTime endLocalDateTime = localDateTime.toLocalDate().atTime(23, 59, 59);
        return ratesRepository.findAllByFromContainsIgnoreCaseAndToContainsIgnoreCaseAndTimestampBetweenOrderByTimestampAsc(from, to, startLocalDateTime, endLocalDateTime);
    }


}
