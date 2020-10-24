package org.tpokora.users.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.persistance.repositories.users.UserRepository;
import org.tpokora.persistance.entity.users.User;

import java.util.List;

@Profile("!nodb")
@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

    private static final Logger logger = LoggerFactory.getLogger(UsersRestController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
