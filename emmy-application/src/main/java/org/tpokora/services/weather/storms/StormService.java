package org.tpokora.services.weather.storms;


import org.tpokora.services.soap.ISoapRequestMessageProcessor;
import org.tpokora.services.soap.ISoapResponseMessageProcessor;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.weather.properties.StormProperties;

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
