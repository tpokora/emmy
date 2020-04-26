package org.tpokora.storms.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Set;


@Api(value = "Storms", description = "Storms API")
@RestController
@RequestMapping(value = "/api/storms")
public class StormController {

    private static final Logger logger = LoggerFactory.getLogger(StormController.class);

    @Autowired
    FindStormService findStormService;

    @Autowired
    FindCityService findCityService;

    @Autowired
    FindWarningService findWarningService;

    @ApiOperation(value = "Find storm", notes = "Find storm by x, y coordinates and radius im km")
    @RequestMapping(value = "/find_storm/", method = RequestMethod.GET)
    public ResponseEntity<Object> getStormByCoordinates(@RequestParam("x") Double x, @RequestParam("y") Double y,
                                                       @RequestParam("radius") int radius) throws Exception {
        StormRequest stormRequest = new StormRequest();
        stormRequest.setCoordinates(new Coordinates(x, y));
        stormRequest.setDistance(radius);

        return new ResponseEntity<>(findStormService.checkStorm(stormRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Find city", notes = "Returns coordinates of given city")
    @RequestMapping(value = "/city/", method = RequestMethod.GET)
    public ResponseEntity<Object> getCityCoordinates(@RequestParam("name") String name) throws Exception {
        City city = findCityService.findCity(name);
        if (city.getCoordinates().getX().compareTo(0.0) == 0 && city.getCoordinates().getY().equals("0")) {
            ErrorMsg errorMsg = new ErrorMsg();
            errorMsg.setError(name + " not found");
            return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
        }

        city.setName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @ApiOperation(value = "Find warnings", notes = "Returns weather condition warnings for x and y coordinates")
    @RequestMapping(value = "/warnings/", method = RequestMethod.GET)
    public ResponseEntity<Object> getWarnings(@RequestParam("x") Double x, @RequestParam("y") Double y) throws Exception {
        Coordinates coordinates = new Coordinates(x, y);
        SOAPMessage warningResponse = findWarningService.findWarning(coordinates);

        Set<Warning> warning = findWarningService.handleResponse(warningResponse);
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
