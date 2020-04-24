package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.processor.CitySoapRequestProcessor;
import org.tpokora.storms.services.processor.CitySoapResponseProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;

@Service
public class FindCityService extends StormService {

    private final Logger LOGGER = LoggerFactory.getLogger(FindCityService.class);

    public FindCityService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
        this.soapRequestMessageProcessor = new CitySoapRequestProcessor(stormProperties);
        this.soapResponseMessageProcessor = new CitySoapResponseProcessor();
    }

    public City findCity(String city) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(city);
        SOAPMessage soapResponse = SOAPService.sendSOAPMessage(soapMessage, URL);
        return (City) soapResponseMessageProcessor.process(soapResponse);
    }
}
