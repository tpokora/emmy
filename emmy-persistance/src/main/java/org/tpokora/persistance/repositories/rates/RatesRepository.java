package org.tpokora.persistance.repositories.rates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RatesRepository extends JpaRepository<RateEntity, Integer> {

    List<RateEntity> findAllByFromContainsIgnoreCaseAndToContainsIgnoreCaseAndTimestampBetween(String from,
                                                                                 String to,
                                                                                 LocalDateTime timestampStart,
                                                                                 LocalDateTime timestampEnd);
}
