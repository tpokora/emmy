package org.tpokora.application.rates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.rates.properties.GoldAPIProperties;
import org.tpokora.application.rates.services.GoldAPIRatesService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class GoldAPIRatesServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Disabled
    @Test
    void testFindRate() {
        GoldAPIRatesService goldAPIRatesService = new GoldAPIRatesService(restTemplate, new GoldAPIProperties());
        goldAPIRatesService.findRate("XAU", "USD", LocalDateTime.now());
    }
}