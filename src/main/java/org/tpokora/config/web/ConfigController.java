package org.tpokora.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.config.FirebaseProperties;

@RestController
@RequestMapping(value = "/api/config")
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    FirebaseProperties firebaseProperties;

    @RequestMapping(value = "/firebase", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<FirebaseProperties> getFirebaseConfig() {
        return new ResponseEntity<>(firebaseProperties, HttpStatus.OK);
    }

    @RequestMapping(value = "/firebase/set", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<FirebaseProperties> getFirebaseConfigSet(@RequestParam(value = "clientToken", required = false) String clientToken) {
        if (clientToken != null) {
            firebaseProperties.setClientToken(clientToken);
        }
        return new ResponseEntity<>(firebaseProperties, HttpStatus.OK);
    }
}
