package org.tpokora.storms.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tpokora.common.services.soap.SoapMessageUtilities;
import org.tpokora.common.services.soap.SoapResponseMessageProcessor;
import org.tpokora.common.utils.Resolver;
import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningResolver;
import org.tpokora.storms.model.WarningStrings;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.tpokora.common.services.soap.SoapMessageUtilities.elementValue;

public class WarningsSoapResponseProcessor implements SoapResponseMessageProcessor<List<Warning>> {

    private final Logger LOGGER = LoggerFactory.getLogger(WarningsSoapResponseProcessor.class);

    private Resolver warningResolver;

    public WarningsSoapResponseProcessor() {
        warningResolver = new WarningResolver();
    }

    @Override
    public List<Warning> process(SOAPMessage soapMessage) throws SOAPException {
        LOGGER.debug("Process SOAP Warnings Response");
        LOGGER.debug(SoapMessageUtilities.soapMessageToString(soapMessage));
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node response = (Node) soapBody.getElementsByTagName("ns1:ostrzezenia_pogodoweResponse").item(0);
        org.w3c.dom.Node returnElem = response.getParentElement().getElementsByTagName("return").item(0);
        List<Warning> warnings = new ArrayList<>();

        addWarning("mroz", returnElem, warnings);
        addWarning("upal", returnElem, warnings);
        addWarning("wiatr", returnElem, warnings);
        addWarning("opad", returnElem, warnings);
        addWarning("burza", returnElem, warnings);
        addWarning("traba", returnElem, warnings);

        warnings.sort(Comparator.comparingInt(Warning::getLevel));

        LOGGER.debug("Warnings: " + warnings);
        return warnings;
    }

    private void addWarning(String warningName, org.w3c.dom.Node returnElem, List<Warning> warnings) {
        int level = Integer.parseInt(elementValue(returnElem, warningName));
        if (level > 0) {
            Period period = new Period(elementValue(returnElem, String.format("%s_od_dnia", warningName)),
                    elementValue(returnElem, String.format("%s_do_dnia", warningName)),
                    WarningStrings.WARNINGS_DATE_FORMAT);

            Warning warning = Warning.builder()
                    .name((String) warningResolver.resolve(warningName))
                    .level(level)
                    .period(period)
                    .build();

            warnings.add(warning);
        }
    }
}
