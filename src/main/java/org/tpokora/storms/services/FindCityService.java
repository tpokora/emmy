package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.processor.CityResponseSoapProcessor;
import org.tpokora.storms.services.processor.SoapMessageProcessor;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FindCityService extends StormService {

    private final Logger LOGGER = LoggerFactory.getLogger(FindCityService.class);

    private SoapMessageProcessor soapMessageProcessor;

    public FindCityService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
        this.soapMessageProcessor = new CityResponseSoapProcessor();
    }

    public City findCity(String city) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(SOAP, NAMESPACE);
        SOAPEnvelope envelope = soapService.createSOAPEnvelope(soapMessage, namespaces);

        soapService.createSOAPHeader(soapMessage, SOAP_ACTION_SZUKAJ_BURZY);
        createSOAPMessage(city, SOAP, envelope);

        LOGGER.debug("Request SOAP Message:");
        LOGGER.debug(printMsg(soapMessage));

        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, URL);

        return (City) soapMessageProcessor.process(soapResponse);
    }

    private void createSOAPMessage(String city, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findCity = soapBody.addChildElement(METHOD_MIEJSCOWOSC, namespace);
        SOAPElement nameElem = findCity.addChildElement("nazwa", namespace);
        SOAPElement keyElem = findCity.addChildElement("klucz", namespace);

        nameElem.addTextNode(city);
        keyElem.addTextNode(stormProperties.getStorm().get(StormProperties.KEY));
    }
}
