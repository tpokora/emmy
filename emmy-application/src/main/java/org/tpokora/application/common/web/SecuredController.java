package org.tpokora.application.common.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/security")
    public String secured() {
        return "Secured";
    }
}
