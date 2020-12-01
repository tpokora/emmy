package org.tpokora.application.rates.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.utils.FileReaderUtils;
import org.tpokora.application.rates.properties.GoldAPIProperties;
import org.tpokora.application.rates.services.api.GoldAPIRatesService;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GoldAPIRatesServiceTest {

    public static final String XAU = "XAU";
    public static final String USD = "USD";
    public static final double VALUE = 1912.4;
    public static final String DATE_STRING = "2020-10-09 09:30:00";
    public static final String GOLD_API_RATE_NAME = "Gold API Rate";

    @Mock
    private GoldAPIProperties goldAPIProperties;

    @Mock
    RestTemplate restTemplate;

    GoldAPIRatesService goldAPIRatesService;

    @BeforeEach
    public void setup()
    {
        goldAPIRatesService = new GoldAPIRatesService(restTemplate, new GoldAPIProperties());
        Mockito.when(goldAPIProperties.getValue(GoldAPIProperties.KEY)).thenReturn("testKey");

    }

    @Test
    void testFindRate() {
        String fileToString = FileReaderUtils.fileToString("rates/goldAPIRatesReponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<RateEntity> rateOptional = goldAPIRatesService.findRate("XAU", "USD", LocalDateTime.now());
        Assertions.assertTrue(rateOptional.isPresent());
        RateEntity rateEntity = rateOptional.get();

        Assertions.assertEquals(GOLD_API_RATE_NAME, rateEntity.getName());
        Assertions.assertEquals(XAU, rateEntity.getFrom());
        Assertions.assertEquals(USD, rateEntity.getTo());
        Assertions.assertEquals(VALUE, rateEntity.getValue());
        Assertions.assertEquals(DATE_STRING, DateUtils.parseDateToString(rateEntity.getTimestamp()));
    }

    @Test
    void testFindRate_empty() {
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>("", null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<RateEntity> rateOptional = goldAPIRatesService.findRate("XAU", "USD", LocalDateTime.now());
        Assertions.assertTrue(rateOptional.isEmpty());
    }
}
