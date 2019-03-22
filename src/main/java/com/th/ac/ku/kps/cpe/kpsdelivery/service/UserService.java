package com.th.ac.ku.kps.cpe.kpsdelivery.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> readUserById(Integer id_user);
}