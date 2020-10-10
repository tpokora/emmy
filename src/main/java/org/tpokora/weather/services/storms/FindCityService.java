package org.tpokora.weather.services.storms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.domain.weather.Location;
import org.tpokora.weather.services.processor.LocationSoapRequestProcessor;
import org.tpokora.weather.services.processor.LocationSoapResponseProcessor;

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
