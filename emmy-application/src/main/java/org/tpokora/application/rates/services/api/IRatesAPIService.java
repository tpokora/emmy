package org.tpokora.application.rates.services.api;

import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IRatesAPIService {

    public Optional<RateEntity> findRate(String from, String to, LocalDateTime dateTime);
}
