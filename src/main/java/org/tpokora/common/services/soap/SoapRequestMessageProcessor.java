package org.tpokora.common.services.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SoapRequestMessageProcessor<T> {

    public SOAPMessage process(T input) throws SOAPException;
}
