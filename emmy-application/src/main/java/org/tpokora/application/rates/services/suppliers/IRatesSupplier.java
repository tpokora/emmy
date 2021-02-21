package org.tpokora.application.rates.services.suppliers;

import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IRatesSupplier {

    public Optional<RateEntity> findRate(String from, String to, LocalDateTime dateTime);
}
