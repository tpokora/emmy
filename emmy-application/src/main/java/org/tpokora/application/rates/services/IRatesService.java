package org.tpokora.application.rates.services;

import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IRatesService {

    RateEntity findRateForDate(String from, String to, LocalDateTime localDateTime);
    List<RateEntity> findArchivedRateForDate(String from, String to, LocalDateTime localDateTime);
    List<RateEntity> findArchivedRates(String from, String to, LocalDateTime startDate, LocalDateTime endDate);
    RateEntity saveRate(RateEntity rateEntity);
}
