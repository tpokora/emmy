package org.tpokora.storms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.common.model.ErrorMsg;
import org.tpokora.storms.model.StormRequest;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;


@RestController
public class StormController {

    @Autowired
    FindStormService findStormService;

    @RequestMapping(value = "/storm/", method = RequestMethod.GET)
    public ResponseEntity<Object> getStormByCordinates(@RequestParam("x") String x, @RequestParam("y") String y,
                                                       @RequestParam("radius") int radius) throws Exception {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setX(x);
        stormRequest.setY(y);
        stormRequest.setDistance(radius);

        SOAPMessage stormResponse = findStormService.checkStorm(stormRequest);

        if (checkForError(stormResponse) != null) {
            return new ResponseEntity<>(checkForError(stormResponse), HttpStatus.OK);
        }

        return new ResponseEntity<>(findStormService.handleResponse(stormResponse), HttpStatus.OK);
    }

    private ErrorMsg checkForError(SOAPMessage soapMessage) throws SOAPException {
        SOAPFault fault = soapMessage.getSOAPBody().getFault();
        if (fault != null) {
            ErrorMsg errorMsg = new ErrorMsg();
            errorMsg.setError(fault.getFaultString());
            return errorMsg;
        }
        return null;
    }

}
