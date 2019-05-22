package com.th.ac.ku.kps.cpe.kpsdelivery.controller;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.OrderRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.RestaurantRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserInfoRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.service.PushNotificationsService;
import com.th.ac.ku.kps.cpe.kpsdelivery.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping(path = "/kpsdelivery")
public class DriverController implements Serializable {

    private static PushNotificationsService pushNotificationsService;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DriverController(PushNotificationsService pushNotificationsService,UserRepository userRepository,UserInfoRepository userInfoRepository, OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.pushNotificationsService = pushNotificationsService;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //start here!!
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<?> userGetResponse(@RequestHeader String token, @PathVariable("id") int id) {
        UserServiceImpl userService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
        return userService.getUser(token, id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/user")
    public ResponseEntity<?> userUpdateResponse(@RequestHeader String token, @RequestBody userUpdateRequest restRequest) {
        UserServiceImpl userService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
        return userService.updateUser(token, restRequest);
    }

//    @RequestMapping(method = RequestMethod.POST,value = "/order")
//////    public ResponseEntity<?> orderCreateResponse(@RequestHeader String token, @RequestBody OrderCreateBodyRequest restRequest) {
//////        UserServiceImpl buyerService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
//////        return buyerService.orderCreate(token, restRequest);
//////    }


    @RequestMapping(method = RequestMethod.PATCH,value = "/order")
    public ResponseEntity<?> orderUpdateResponse(@RequestHeader String token, @RequestBody OrderUpdateRequest restRequest) {
        UserServiceImpl orderService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
        return orderService.orderUpdate(token, restRequest);
    }

    //find driver
    @RequestMapping(method = RequestMethod.GET,value = "/finder")
    public ResponseEntity<?> driverFindResponse(@RequestHeader String token, @RequestBody DriverFindRequest restRequest) {
        UserServiceImpl driverService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
        return driverService.driverFind(token, restRequest);
    }
}