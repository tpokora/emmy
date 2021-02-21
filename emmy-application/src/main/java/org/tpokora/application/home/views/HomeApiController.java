package org.tpokora.application.home.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeApiController.class);

    @CrossOrigin
    @GetMapping("/api/hello")
    public String hello() {
        String message = "Hello, the time at the server is now " + LocalDateTime.now();
        LOGGER.info(">>> {}", message);
        return message;
    }
}
