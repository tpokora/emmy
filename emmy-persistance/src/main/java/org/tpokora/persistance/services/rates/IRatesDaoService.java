package org.tpokora.persistance.services.rates;

import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IRatesDaoService {

    public RateEntity saveRate(RateEntity rateEntity);

    public List<RateEntity> getRatesBetweenDates(String from, String to, LocalDateTime startDate, LocalDateTime endDate);

    public List<RateEntity> getRatesDaysBeforeToday(String from, String to, int minusDays);

    public List<RateEntity> getRatesForDate(String from, String to, LocalDateTime localDateTime);
}
