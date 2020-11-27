package org.tpokora.application.rates.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.mapper.IJSONMapper;
import org.tpokora.application.rates.mapper.GoldAPIMapper;
import org.tpokora.application.rates.properties.GoldAPIProperties;
import org.tpokora.application.weather.properties.OpenWeatherProperties;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class RatesService implements IRatesService {

    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String DATE = "date";

    private final Logger LOGGER = LoggerFactory.getLogger(RatesService.class);

    private final RestTemplate restTemplate;
    private final IJSONMapper IJSONMapper;
    private final GoldAPIProperties goldAPIProperties;

    public static final String URL = "https://www.goldapi.io/api/{from}/{to}/{date}";

    public RatesService(RestTemplate restTemplate, GoldAPIProperties goldAPIProperties) {
        this.restTemplate = restTemplate;
        this.goldAPIProperties = goldAPIProperties;
        IJSONMapper = new GoldAPIMapper();
    }

    @Override
    public Optional<RateEntity> findRate(String from, String to, LocalDateTime localDateTime) {
        LOGGER.info(">>> Find rate from: {}, to: {} from date: {}", from, to, DateUtils.parseDateToString(localDateTime));
        HttpEntity request = new HttpEntity(setupHeaders());

        ResponseEntity<String> responseEntity =
                this.restTemplate.exchange(URL, HttpMethod.GET, request, String.class, getUriVariables(from, to, localDateTime));
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String ratesString = responseEntity.getBody();
            RateEntity rateEntity = (RateEntity) IJSONMapper.map(ratesString);
            return Optional.of(rateEntity);
        }
        return Optional.empty();
    }

    private HttpHeaders setupHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-access-token", goldAPIProperties.getValue(GoldAPIProperties.KEY));
        httpHeaders.set("Content-Type", "application/json");

        return httpHeaders;
    }

    private Map<String, String> getUriVariables(String from, String to, LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder();
        String dateString = stringBuilder
                .append(localDateTime.getYear())
                .append(formatNumber(localDateTime.getMonthValue()))
                .append(formatNumber(localDateTime.getDayOfMonth()))
                .toString();
        return Map.of(FROM, from, TO, to, DATE, dateString);
    }

    private String formatNumber(int number) {
        if (number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }
}
