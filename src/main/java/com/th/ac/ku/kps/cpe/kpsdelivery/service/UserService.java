package com.th.ac.ku.kps.cpe.kpsdelivery.service;


import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> getUser (String token, int id);
    ResponseEntity<?> updateUser (String token, userUpdateRequest restRequest);
    //ResponseEntity<?> orderCreate(String token,OrderCreateBodyRequest restRequest);
    ResponseEntity<?> orderUpdate(String token, OrderUpdateRequest restRequest);
    ResponseEntity<?> driverFind (String token, DriverFindRequest restRequest);
}