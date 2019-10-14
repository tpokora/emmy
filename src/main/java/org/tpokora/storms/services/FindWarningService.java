package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningStrings;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
        Set<Warning> warnings = new HashSet<>();

//        Warning warning = new Warning();
//        warning.setFromDay(elementValue(returnElem, "od_dnia"));
//        warning.setToDay(elementValue(returnElem, "do_dnia"));

//        warning.setCold(Integer.parseInt(elementValue(returnElem, "mroz")));
//        warning.setColdFromDay(elementValue(returnElem, "mroz_od_dnia"));
//        warning.setColdToDay(elementValue(returnElem, "mroz_do_dnia"));

        int coldLevel = Integer.parseInt(elementValue(returnElem, "mroz"));
        if (coldLevel > 0) {
            Warning frostWarning = new Warning();
            frostWarning.setName(WarningStrings.FROST);
            frostWarning.setLevel(coldLevel);

            Period heatPeriod = new Period(elementValue(returnElem, "mroz_od_dnia"),
                    elementValue(returnElem, "mroz_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            frostWarning.setPeriod(heatPeriod);

            warnings.add(frostWarning);
        }

        int heatLevel = Integer.parseInt(elementValue(returnElem, "upal"));
        if (heatLevel > 0) {
            Warning heatWarning = new Warning();
            heatWarning.setName(WarningStrings.HEAT);
            heatWarning.setLevel(heatLevel);

            Period heatPeriod = new Period(elementValue(returnElem, "upal_od_dnia"),
                    elementValue(returnElem, "upal_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            heatWarning.setPeriod(heatPeriod);

            warnings.add(heatWarning);
        }

//        warning.setWind(Integer.parseInt(elementValue(returnElem, "wiatr")));
//        warning.setWindFromDay(elementValue(returnElem, "wiatr_od_dnia"));
//        warning.setWindToDay(elementValue(returnElem, "wiatr_do_dnia"));

        int windLevel = Integer.parseInt(elementValue(returnElem, "wiatr"));
        if (windLevel > 0) {
            Warning windWarning = new Warning();
            windWarning.setName(WarningStrings.WIND);
            windWarning.setLevel(windLevel);

            Period windPeriod = new Period(elementValue(returnElem, "wiatr_od_dnia"),
                    elementValue(returnElem, "wiatr_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            windWarning.setPeriod(windPeriod);

            warnings.add(windWarning);
        }


//
//        warning.setRain(Integer.parseInt(elementValue(returnElem, "opad")));
//        warning.setRainFromDay(elementValue(returnElem, "opad_od_dnia"));
//        warning.setRainToDay(elementValue(returnElem, "opad_do_dnia"));

        int rainfallLevel = Integer.parseInt(elementValue(returnElem, "opad"));
        if (rainfallLevel > 0) {
            Warning rainfallWarning = new Warning();
            rainfallWarning.setName(WarningStrings.RAINFALL);
            rainfallWarning.setLevel(rainfallLevel);

            Period rainfallPeriod = new Period(elementValue(returnElem, "opad_od_dnia"),
                    elementValue(returnElem, "opad_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            rainfallWarning.setPeriod(rainfallPeriod);

            warnings.add(rainfallWarning);
        }
//
//        warning.setStorm(Integer.parseInt(elementValue(returnElem, "burza")));
//        warning.setStormFromDay(elementValue(returnElem, "burza_od_dnia"));
//        warning.setStormToDay(elementValue(returnElem, "burza_do_dnia"));

        int stormLevel = Integer.parseInt(elementValue(returnElem, "burza"));
        if (stormLevel > 0) {
            Warning stormWarning = new Warning();
            stormWarning.setName(WarningStrings.STORM);
            stormWarning.setLevel(stormLevel);

            Period stormPeriod = new Period(elementValue(returnElem, "burza_od_dnia"),
                    elementValue(returnElem, "burza_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            stormWarning.setPeriod(stormPeriod);

            warnings.add(stormWarning);
        }
//        warning.setWhirlwind(Integer.parseInt(elementValue(returnElem, "traba")));
//        warning.setWhirlwindFromDay(elementValue(returnElem, "traba_od_dnia"));
//        warning.setWhirlwindToDay(elementValue(returnElem, "traba_do_dnia"));

        int whirlwindLevel = Integer.parseInt(elementValue(returnElem, "traba"));
        if (whirlwindLevel > 0) {
            Warning whirlwindWarning = new Warning();
            whirlwindWarning.setName(WarningStrings.WHIRLWIND);
            whirlwindWarning.setLevel(whirlwindLevel);

            Period whirlwindPeriod = new Period(elementValue(returnElem, "traba_od_dnia"),
                    elementValue(returnElem, "burza_do_dnia"),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            whirlwindWarning.setPeriod(whirlwindPeriod);

            warnings.add(whirlwindWarning);
        }

        return warnings;
    }

    private void createSOAPMessage(Coordinates coordinates, String namespace, SOAPEnvelope envelope) throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement findStorm = soapBody.addChildElement(METHOD_OSTRZEZENIA, namespace);
        SOAPElement xElem = findStorm.addChildElement("x", namespace);
        SOAPElement yElem = findStorm.addChildElement("y", namespace);
        SOAPElement keyElem = findStorm.addChildElement("klucz", namespace);

        xElem.addTextNode(String.valueOf(coordinates.getX()));
        yElem.addTextNode(String.valueOf(coordinates.getY()));
        keyElem.addTextNode(stormProperties.getStorm().get(StormProperties.KEY));
    }
}
