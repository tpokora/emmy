package org.tpokora.storms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;


@RestController
public class StormController {

    public static final String SOAP = "soap";
    private final String NAMESPACE = "http://burze.dzis.net/soap.php";
    private final String URL = "http://burze.dzis.net/soap.php";
    private final String SOAP_ACTION_KEY_API = "http://burze.dzis.net/soap.php#KeyAPI";
    private final String SOAP_ACTION_MIEJSCOWOSC = "http://burze.dzis.net/soap.php#miejscowosc";
    private final String SOAP_ACTION_OSTRZEZENIA = "http://burze.dzis.net/soap.php#ostrzezenia_pogodowe";
    private final String SOAP_ACTION_SZUKAJ_BURZY = "http://burze.dzis.net/soap.php#szukaj_burzy";
    private final String METHOD_KEY_API = "KeyAPI";
    private final String METHOD_MIEJSCOWOSC = "miejscowosc";
    private final String METHOD_OSTRZEZENIA = "ostrzezenia_pogodowe";
    private final String METHOD_SZUKAJ_BURZY = "szukaj_burzy";
//    private PropertyInfo pfApiKey;

    @Autowired
    StormService stormService;

    @RequestMapping(value = "/storm/", method = RequestMethod.GET)
    public ResponseEntity<Object> getStormByCordinates(@RequestParam("x") String x, @RequestParam("y") String y,
                                                       @RequestParam("radius") int radius) throws Exception {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setX(x);
        stormRequest.setY(y);
        stormRequest.setDistance(radius);

        SOAPMessage stormResponse = stormService.checkStorm(stormRequest);

        if (checkForError(stormResponse) != null) {
            return new ResponseEntity<>(checkForError(stormResponse), HttpStatus.OK);
        }

        return new ResponseEntity<>(stormService.handleResponse(stormResponse), HttpStatus.OK);
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

    private class ErrorMsg {
        private String error;

        public ErrorMsg() {

        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }


}
