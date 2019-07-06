package com.th.ac.ku.kps.cpe.kpsdelivery.controller;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception.AcceptionRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.*;
import com.th.ac.ku.kps.cpe.kpsdelivery.service.PushNotificationsService;
import com.th.ac.ku.kps.cpe.kpsdelivery.service.UserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;


@CrossOrigin
@RestController
@RequestMapping(path = "/kpsdelivery")
public class DriverController implements Serializable {

    private static PushNotificationsService pushNotificationsService;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final UserInfoRepository userInfoRepository;

    private final MenusRepository menusRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DriverController(PushNotificationsService pushNotificationsService, UserRepository userRepository, AccountRepository accountRepository, UserInfoRepository userInfoRepository, MenusRepository menusRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, RestaurantRepository restaurantRepository) {
        this.pushNotificationsService = pushNotificationsService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.userInfoRepository = userInfoRepository;
        this.menusRepository = menusRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //start here!!
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<?> userGetResponse(@RequestHeader String token, @PathVariable("id") int id) {
        UserServiceImpl userService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return userService.getUser(token, id);
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/user")
    public ResponseEntity<?> userUpdateResponse(@RequestHeader String token, @RequestBody userUpdateRequest restRequest) {
        UserServiceImpl userService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return userService.updateUser(token, restRequest);
    }

//    @RequestMapping(method = RequestMethod.POST,value = "/order")
//////    public ResponseEntity<?> orderCreateResponse(@RequestHeader String token, @RequestBody OrderCreateBodyRequest restRequest) {
//////        UserServiceImpl buyerService = new UserServiceImpl(pushNotificationsService,userRepository, userInfoRepository, orderRepository, restaurantRepository);
//////        return buyerService.orderCreate(token, restRequest);
//////    }


    @RequestMapping(method = RequestMethod.POST,value = "/order")
    public ResponseEntity<?> orderUpdateResponse(@RequestHeader String token, @RequestBody OrderUpdateRequest restRequest) {
        UserServiceImpl orderService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return orderService.orderUpdate(token, restRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    public ResponseEntity<?> orderGetResponse(@RequestHeader String token, @PathVariable("id") int id) {
        UserServiceImpl orderService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository,orderDetailsRepository,menusRepository, restaurantRepository);
        return orderService.orderGet(token, id);
    }

    //find driver
    @RequestMapping(method = RequestMethod.GET,value = "/finder/{orderId}/{limit}")
    public ResponseEntity<?> driverFindResponse(@RequestHeader String token, @PathVariable("orderId") int id, @PathVariable("limit") int limit) {
        UserServiceImpl driverService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return driverService.driverFind(token, id, limit);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/acception")
    public ResponseEntity<?> driverAcceptResponse(@RequestHeader String token, @RequestBody AcceptionRequest restRequest) {
        UserServiceImpl driverService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return driverService.acceptionDriver(token, restRequest);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/driver/history/{driverId}")
    public ResponseEntity<?> driverHistoryResponse(@RequestHeader String token, @PathVariable("driverId") int id) {
        UserServiceImpl driverService = new UserServiceImpl(pushNotificationsService,userRepository, accountRepository, userInfoRepository, orderRepository, orderDetailsRepository, menusRepository, restaurantRepository);
        return driverService.driverHistory(token, id);
    }
}