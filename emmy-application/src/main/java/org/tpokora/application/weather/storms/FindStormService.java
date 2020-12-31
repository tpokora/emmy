package org.tpokora.application.weather.storms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.persistance.services.weather.StormDaoService;
import org.tpokora.domain.weather.StormRequest;
import org.tpokora.domain.weather.StormResponse;
import org.tpokora.application.soap.SOAPService;
import org.tpokora.application.weather.processor.StormSoapRequestProcessor;
import org.tpokora.application.weather.processor.StormSoapResponseProcessor;
import org.tpokora.application.weather.properties.StormProperties;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

@Service
public class FindStormService extends StormService {

    private final Logger LOGGER = LoggerFactory.getLogger(FindStormService.class);

    private StormDaoService stormDaoService;

    public FindStormService(StormProperties stormProperties, SOAPService soapService, StormDaoService stormDaoService) {
        super(stormProperties, soapService);
        this.stormDaoService = stormDaoService;
        soapRequestMessageProcessor = new StormSoapRequestProcessor(stormProperties);
        soapResponseMessageProcessor = new StormSoapResponseProcessor();
    }

    public StormResponse checkStorm(StormRequest stormRequest) throws SOAPException {
        LOGGER.info(">>> Find Storm : {}", stormRequest);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(stormRequest);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        StormResponse stormResponse = (StormResponse) soapResponseMessageProcessor.process(soapResponse);
        LOGGER.info(">>> {}", stormResponse);
        stormDaoService.saveStormResponse(stormRequest, stormResponse);
        return stormResponse;
    }
}
