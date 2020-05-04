package org.tpokora.storms.services.processor;

import org.tpokora.common.services.soap.SoapResponseMessageProcessor;
import org.tpokora.storms.model.Warning;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.List;

public class WarningsSoapResponseProcessor implements SoapResponseMessageProcessor<List<Warning>> {

    @Override
    public List<Warning> process(SOAPMessage soapMessage) throws SOAPException {
        return null;
    }
}
