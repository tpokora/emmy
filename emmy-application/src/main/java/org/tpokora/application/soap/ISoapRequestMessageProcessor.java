package org.tpokora.application.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface ISoapRequestMessageProcessor<T> {

    public SOAPMessage process(T input) throws SOAPException;
}
