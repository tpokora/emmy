package org.tpokora.common.services.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SoapResponseMessageProcessor<T> {

    public T process(SOAPMessage soapMessage) throws SOAPException;
}
