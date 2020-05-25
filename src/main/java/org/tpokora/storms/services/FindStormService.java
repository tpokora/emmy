package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.dao.StormDaoService;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.services.processor.StormSoapRequestProcessor;
import org.tpokora.storms.services.processor.StormSoapResponseProcessor;

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
        LOGGER.info("==> Find Storm : {}", stormRequest);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(stormRequest);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        StormResponse stormResponse = (StormResponse) soapResponseMessageProcessor.process(soapResponse);
        LOGGER.info("==> {}", stormResponse);
        stormDaoService.saveStormResponse(stormRequest, stormResponse);
        return stormResponse;
    }
}
