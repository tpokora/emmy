package org.tpokora.storms.services;

import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.common.services.soap.SoapRequestMessageProcessor;
import org.tpokora.common.services.soap.SoapResponseMessageProcessor;
import org.tpokora.config.properties.StormProperties;

@Service
public class StormService {

    protected StormProperties stormProperties;

    protected SoapRequestMessageProcessor soapRequestMessageProcessor;
    protected SoapResponseMessageProcessor soapResponseMessageProcessor;

    protected SOAPService soapService;

    public StormService(StormProperties stormProperties, SOAPService soapService) {
        this.stormProperties = stormProperties;
        this.soapService = soapService;
    }
}
