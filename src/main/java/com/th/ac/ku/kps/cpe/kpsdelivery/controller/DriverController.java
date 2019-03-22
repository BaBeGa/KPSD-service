package com.th.ac.ku.kps.cpe.kpsdelivery.controller;

import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping(path = "/kpsdelivery")
public class DriverController implements Serializable {

    private final UserRepository userRepository;

    @Autowired
    public DriverController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public ResponseEntity<?> orderReadAllResponse() {
        UserServiceImpl buyerService = new UserServiceImpl(userRepository);
        return buyerService.readUserById(1);
    }
}