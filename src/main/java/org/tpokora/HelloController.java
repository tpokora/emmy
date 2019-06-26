package org.tpokora;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<HelloPage> hello() {
        return new ResponseEntity<>(new HelloPage("Hello from Emmy"), HttpStatus.OK);
    }

    public class HelloPage {

        private String message;

        public HelloPage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
