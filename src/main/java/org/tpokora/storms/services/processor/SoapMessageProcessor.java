package org.tpokora.storms.services.processor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SoapMessageProcessor<T> {

    public T process(SOAPMessage soapMessage) throws SOAPException;
}
