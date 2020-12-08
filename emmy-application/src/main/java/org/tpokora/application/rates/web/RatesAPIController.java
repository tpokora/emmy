package org.tpokora.application.rates.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.rates.properties.GoldAPIProperties;
import org.tpokora.application.rates.services.IRatesService;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.rates.RateEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/rates")
public class RatesAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatesAPIController.class);

    private final RestTemplate restTemplate;
    private IRatesService ratesService;
    private GoldAPIProperties goldAPIProperties;

    public RatesAPIController(RestTemplate restTemplate, IRatesService ratesService, GoldAPIProperties goldAPIProperties) {
        this.restTemplate = restTemplate;
        this.goldAPIProperties = goldAPIProperties;
        this.ratesService = ratesService;
    }

    @GetMapping(value = "/getRate", produces = "application/json")
    public ResponseEntity<RateEntity> getRate(@RequestParam("from") String from,
                                              @RequestParam("to")String to,
                                              @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LOGGER.info(">> Find Rate from: {}, to: {} from date: {}", from, to, DateUtils.parseDateToString(localDateTime));
        Optional<RateEntity> rateOptional = ratesService.findRateForDate(from, to, localDateTime);
        if (rateOptional.isPresent()) {
            RateEntity rateEntity = rateOptional.get();
            ratesService.saveRate(rateEntity);
            return new ResponseEntity<>(rateEntity, HttpStatus.OK);
        }
        LOGGER.info(">> Rate not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getCurrentRate", produces = "application/json")
    public ResponseEntity<RateEntity> getCurrentRate(@RequestParam("from") String from,
                                              @RequestParam("to")String to) {

        return getRate(from, to, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
    }
}
