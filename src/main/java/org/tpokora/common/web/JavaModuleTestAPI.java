package org.tpokora.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.domain.TestDomainModuleModel;

@RestController
@RequestMapping(value = "/api/test-api")
public class JavaModuleTestAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaModuleTestAPI.class);

    @GetMapping("domain-model")
    public ResponseEntity<TestDomainModuleModel> getTestModel() {
        TestDomainModuleModel testDomainModuleModel = new TestDomainModuleModel("TEST");
        return new ResponseEntity<>(testDomainModuleModel, HttpStatus.OK);
    }
}
