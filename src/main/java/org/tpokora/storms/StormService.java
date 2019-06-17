package org.tpokora.storms;

import com.sun.xml.messaging.saaj.soap.impl.ElementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.tpokora.common.web.SOAPService;

@Service
public class StormService {

    @Autowired
    protected SOAPService soapService;

    @Autowired
    protected Environment environment;

    protected String elementValue(org.w3c.dom.Node element, String name) {
        return ((ElementImpl) element).getElementsByTagName(name).item(0).getTextContent();
    }
}
