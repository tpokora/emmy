package org.tpokora.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;

import java.util.List;

@Profile("!nodb")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
