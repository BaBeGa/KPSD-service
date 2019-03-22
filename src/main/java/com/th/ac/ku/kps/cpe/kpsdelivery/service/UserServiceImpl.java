package com.th.ac.ku.kps.cpe.kpsdelivery.service;

import com.th.ac.ku.kps.cpe.kpsdelivery.UsersEntity;

import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> readUserById(Integer id_user) {
        UsersEntity user = userRepository.findByIdUser(id_user);
        return ResponseEntity.ok(user);
    }
}
