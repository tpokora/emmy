package org.tpokora.storms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.common.model.ErrorMsg;
import org.tpokora.storms.model.City;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.storms.services.FindStormService;
import org.tpokora.storms.services.FindWarningService;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;


@RestController
@RequestMapping(value = "/storms/")
public class StormController {

    @Autowired
    FindStormService findStormService;

    @Autowired
    FindCityService findCityService;

    @Autowired
    FindWarningService findWarningService;

    @RequestMapping(value = "/find_storm/", method = RequestMethod.GET)
    public ResponseEntity<Object> getStormByCordinates(@RequestParam("x") String x, @RequestParam("y") String y,
                                                       @RequestParam("radius") int radius) throws Exception {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(new Coordinates(x, y));
        stormRequest.setDistance(radius);

        SOAPMessage stormResponse = findStormService.checkStorm(stormRequest);

        if (checkForError(stormResponse) != null) {
            return new ResponseEntity<>(checkForError(stormResponse), HttpStatus.OK);
        }

        return new ResponseEntity<>(findStormService.handleResponse(stormResponse), HttpStatus.OK);
    }

    @RequestMapping(value = "/city/", method = RequestMethod.GET)
    public ResponseEntity<Object> getCityCordinates(@RequestParam("name") String name) throws Exception {
        SOAPMessage stormResponse = findCityService.findCity(name);

        if (checkForError(stormResponse) != null) {
            return new ResponseEntity<>(checkForError(stormResponse), HttpStatus.OK);
        }

        City city = findCityService.handleResponse(stormResponse);
        if (city.getCoordinates().getX().equals("0") && city.getCoordinates().getY().equals("0")) {
            ErrorMsg errorMsg = new ErrorMsg();
            errorMsg.setError(name + " not found");
            return new ResponseEntity<>(errorMsg, HttpStatus.OK);
        }

        city.setName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/warnings/", method = RequestMethod.GET)
    public ResponseEntity<Object> getWarnings(@RequestParam("x") String x, @RequestParam("y") String y) throws Exception {
        Coordinates coordinates = new Coordinates(x, y);
        SOAPMessage warningResponse = findWarningService.findWarning(coordinates);

        Warning warning = findWarningService.handleResponse(warningResponse);
        return new ResponseEntity<>(warning, HttpStatus.OK);
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
