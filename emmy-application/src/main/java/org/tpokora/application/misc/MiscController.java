package org.tpokora.application.misc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/misc/")
public class MiscController {

    @RequestMapping(value = "redirect")
    public ResponseEntity<String> redirectEndpoint() {
        return new ResponseEntity<>("redirect", HttpStatus.FOUND);
    }
}
