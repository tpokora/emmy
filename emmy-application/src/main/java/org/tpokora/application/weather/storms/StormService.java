package org.tpokora.application.weather.storms;


import org.tpokora.application.soap.ISoapRequestMessageProcessor;
import org.tpokora.application.soap.ISoapResponseMessageProcessor;
import org.tpokora.application.soap.SOAPService;
import org.tpokora.application.weather.properties.StormProperties;

public abstract class StormService {

    protected StormProperties stormProperties;

    protected ISoapRequestMessageProcessor soapRequestMessageProcessor;
    protected ISoapResponseMessageProcessor soapResponseMessageProcessor;

    protected SOAPService soapService;

    public StormService(StormProperties stormProperties, SOAPService soapService) {
        this.stormProperties = stormProperties;
        this.soapService = soapService;
    }
}
