package org.tpokora.weather.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.weather.model.City;
import org.tpokora.weather.services.processor.CitySoapRequestProcessor;
import org.tpokora.weather.services.processor.CitySoapResponseProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Objects;

@Service
public class FindCityService extends StormService {

    private static final String CITY_NAME_IS_NULL = "City name is null!";

    private final Logger LOGGER = LoggerFactory.getLogger(FindCityService.class);

    public FindCityService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
        this.soapRequestMessageProcessor = new CitySoapRequestProcessor(stormProperties);
        this.soapResponseMessageProcessor = new CitySoapResponseProcessor();
    }

    public City findCity(String cityName) throws SOAPException {
        Objects.requireNonNull(cityName, CITY_NAME_IS_NULL);
        LOGGER.info("==> {}", cityName);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(cityName);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        City city = (City) soapResponseMessageProcessor.process(soapResponse);
        city.setName(cityName);
        LOGGER.info("==> {}", city);
        return city;
    }
}
