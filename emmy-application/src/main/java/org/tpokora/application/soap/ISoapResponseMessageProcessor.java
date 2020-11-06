package org.tpokora.application.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface ISoapResponseMessageProcessor<T> {

    public T process(SOAPMessage soapMessage) throws SOAPException;
}
