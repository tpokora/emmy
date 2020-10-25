package org.tpokora.persistance.repositories.rates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.persistance.entity.rates.RateEntity;

public interface RatesRepository extends JpaRepository<RateEntity, Integer> {

}
