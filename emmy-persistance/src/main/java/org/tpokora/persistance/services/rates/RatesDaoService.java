package org.tpokora.persistance.services.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.persistance.entity.rates.RateEntity;
import org.tpokora.persistance.repositories.rates.RatesRepository;

import java.util.Objects;

@Service
public class RatesDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(RatesDaoService.class);

    private final RatesRepository ratesRepository;

    public RatesDaoService(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public RateEntity saveRate(RateEntity rateEntity) {
        Objects.requireNonNull(rateEntity, "RateEntity can't be null!");
        LOGGER.info(">>> Saving Forecast to DB");
        return ratesRepository.save(rateEntity);
    }
}
