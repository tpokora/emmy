package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningStrings;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FindWarningService extends StormService {

    private static final Logger logger = LoggerFactory.getLogger(FindWarningService.class);

    public FindWarningService(StormProperties stormProperties, SOAPService soapService) {
        super(stormProperties, soapService);
    }

    public SOAPMessage findWarning(Coordinates coordinates) throws SOAPException, IOException {
        SOAPMessage soapMessage = soapService.createSOAPMessage();
        HashMap<String, String> namespaces = new HashMap<>();
        namespaces.put(SOAP, NAMESPACE);
        SOAPEnvelope envelope = soapService.createSOAPEnvelope(soapMessage, namespaces);

        soapService.createSOAPHeader(soapMessage, SOAP_ACTION_OSTRZEZENIA);
        createSOAPMessage(coordinates, SOAP, envelope);

        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();
        soapMessage.saveChanges();

        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, URL);

        System.out.println("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        System.out.println();

        return soapResponse;
    }

    public Set<Warning> handleResponse(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:ostrzezenia_pogodoweResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        List<Warning> warnings = new ArrayList<>();

        int coldLevel = Integer.parseInt(elementValue(returnElem, "mroz"));
        if (coldLevel > 0) {
            Period coldPeriod = new Period(elementValue(returnElem, "mroz_od_dnia"),
                    elementValue(returnElem, "mroz_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning frostWarning = Warning.builder()
                    .name(WarningStrings.FROST)
                    .level(coldLevel)
                    .period(coldPeriod)
                    .build();

            warnings.add(frostWarning);
        }

        int heatLevel = Integer.parseInt(elementValue(returnElem, "upal"));
        if (heatLevel > 0) {
            Period heatPeriod = new Period(elementValue(returnElem, "upal_od_dnia"),
                    elementValue(returnElem, "upal_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning heatWarning = Warning.builder()
                    .name(WarningStrings.HEAT)
                    .level(heatLevel)
                    .period(heatPeriod)
                    .build();

            warnings.add(heatWarning);
        }

        int windLevel = Integer.parseInt(elementValue(returnElem, "wiatr"));
        if (windLevel > 0) {
            Period windPeriod = new Period(elementValue(returnElem, "wiatr_od_dnia"),
                    elementValue(returnElem, "wiatr_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);
            Warning windWarning = Warning.builder()
                    .name(WarningStrings.WIND)
                    .level(windLevel)
                    .period(windPeriod)
                    .build();

            warnings.add(windWarning);
        }

        int rainfallLevel = Integer.parseInt(elementValue(returnElem, "opad"));
        if (rainfallLevel > 0) {
            Period rainfallPeriod = new Period(elementValue(returnElem, "opad_od_dnia"),
                    elementValue(returnElem, "opad_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning rainfallWarning = Warning.builder()
                    .name(WarningStrings.RAINFALL)
                    .level(rainfallLevel)
                    .period(rainfallPeriod)
                    .build();

            warnings.add(rainfallWarning);
        }

        int stormLevel = Integer.parseInt(elementValue(returnElem, "burza"));
        if (stormLevel > 0) {
            Period stormPeriod = new Period(elementValue(returnElem, "burza_od_dnia"),
                    elementValue(returnElem, "burza_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning stormWarning = Warning.builder()
                    .name(WarningStrings.STORM)
                    .level(stormLevel)
                    .period(stormPeriod)
                    .build();

            warnings.add(stormWarning);
        }

        int whirlwindLevel = Integer.parseInt(elementValue(returnElem, "traba"));
        if (whirlwindLevel > 0) {
            Period whirlwindPeriod = new Period(elementValue(returnElem, "traba_od_dnia"),
                    elementValue(returnElem, "burza_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning whirlwindWarning = Warning.builder()
                    .name(WarningStrings.WHIRLWIND)
                    .level(whirlwindLevel)
                    .period(whirlwindPeriod)
                    .build();

            warnings.add(whirlwindWarning);
        }

        return warnings.stream()
                .sorted(Comparator.comparing(Warning::getLevel))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void createSOAPMessage(Coordinates coordinates, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(METHOD_OSTRZEZENIA, namespace);
        SOAPElement xElem = findStorm.addChildElement("x", namespace);
        SOAPElement yElem = findStorm.addChildElement("y", namespace);
        SOAPElement keyElem = findStorm.addChildElement("klucz", namespace);

        xElem.addTextNode(String.valueOf(coordinates.getX()));
        yElem.addTextNode(String.valueOf(coordinates.getY()));
        keyElem.addTextNode(stormProperties.getValue(StormProperties.KEY));
    }
}
