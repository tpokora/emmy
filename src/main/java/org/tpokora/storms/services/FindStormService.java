package org.tpokora.storms.services;

import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.services.processor.StormSoapRequestProcessor;
import org.tpokora.storms.services.processor.StormSoapResponseProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;

@Service
public class FindStormService extends StormService {

    public FindStormService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
        soapRequestMessageProcessor = new StormSoapRequestProcessor(stormProperties);
        soapResponseMessageProcessor = new StormSoapResponseProcessor();
    }

    public StormResponse checkStorm(StormRequest stormRequest) throws SOAPException {
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(stormRequest);
        SOAPMessage soapResponse = SOAPService.sendSOAPMessage(soapMessage, URL);
        return (StormResponse) soapResponseMessageProcessor.process(soapResponse);
    }
}
