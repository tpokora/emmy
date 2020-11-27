package org.tpokora.application.rates;

import org.springframework.stereotype.Service;

import org.tpokora.persistance.services.rates.RatesDaoService;

@Service
public class FindRatesService {

    private RatesDaoService ratesDaoService;

    public FindRatesService(RatesDaoService ratesDaoService) {
        this.ratesDaoService = ratesDaoService;
    }
}
