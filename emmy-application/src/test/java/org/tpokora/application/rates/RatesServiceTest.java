package org.tpokora.application.rates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.rates.services.RatesService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class RatesServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Disabled
    @Test
    void testFindRate() {
        RatesService ratesService = new RatesService(restTemplate);
        ratesService.findRate("XAU", "USD", LocalDateTime.now());
    }
}