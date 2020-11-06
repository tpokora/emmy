package org.tpokora.services.weather.storms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.domain.weather.Location;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.weather.processor.LocationSoapRequestProcessor;
import org.tpokora.services.weather.processor.LocationSoapResponseProcessor;
import org.tpokora.services.weather.properties.StormProperties;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Objects;

@Service
public class FindCityService extends StormService {

    private static final String CITY_NAME_IS_NULL = "City name is null!";

    private final Logger LOGGER = LoggerFactory.getLogger(FindCityService.class);

    public FindCityService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
        this.soapRequestMessageProcessor = new LocationSoapRequestProcessor(stormProperties);
        this.soapResponseMessageProcessor = new LocationSoapResponseProcessor();
    }

    public Location findCity(String cityName) throws SOAPException {
        Objects.requireNonNull(cityName, CITY_NAME_IS_NULL);
        LOGGER.info(">>> {}", cityName);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(cityName);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        Location location = (Location) soapResponseMessageProcessor.process(soapResponse);
        location.setName(cityName);
        LOGGER.info(">>> {}", location);
        return location;
    }
}
