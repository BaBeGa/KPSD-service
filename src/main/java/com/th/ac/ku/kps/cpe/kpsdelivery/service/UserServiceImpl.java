package com.th.ac.ku.kps.cpe.kpsdelivery.service;

import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.*;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;
//import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.create.OrderCreateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception.AcceptionRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception.AcceptionResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.get.userGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.get.driverorderGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.get.orderGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.*;
import com.th.ac.ku.kps.cpe.kpsdelivery.unity.Common;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
@EnableAsync
public class UserServiceImpl implements UserService {
    private static final String FIREBASE_SERVER_KEY = "AAAAsK3sCOk:APA91bEiZieHEJVIzO9mXIes3iMZGQKRGFkbCMTiTtKX5lFBtMg7rVuNWoVA04biinL3b1jlGlUwHsLlaqBvgsHM7BGZVAoo9PKbp1f_UEvWfUjjNzM7KmGiUp4FOOWbi68zC33ewwJv";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    //private static PushNotificationsService pushNotificationsService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final UserInfoRepository userInfoRepository;
    private final MenusRepository menusRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final RestaurantRepository restaurantRepository;
    public UserServiceImpl(PushNotificationsService pushNotificationsService, UserRepository userRepository, AccountRepository accountRepository, UserInfoRepository userInfoRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, MenusRepository menusRepository, RestaurantRepository restaurantRepository) {
        //this.pushNotificationsService = pushNotificationsService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.userInfoRepository = userInfoRepository;
        this.menusRepository = menusRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //start here!!!
    @Override
    public ResponseEntity<?> getUser (String token, int id) {
        UsersEntity userEntity = userRepository.findById(id);
        AccountsEntity accountsEntity = accountRepository.findByUserId(id);
        userGetResponse response = new userGetResponse();
        try{
            if(accountsEntity != null){
                response.setCredit(accountsEntity.getCredit());
            }
            response.setWorkStatus(userEntity.getWorkStatus());
            response.setUserType(userEntity.getType());
            response.setStatus(201);
            response.setMsg("Found user!");
        }catch(Exception e){
            response.setStatus(401);
            response.setMsg("Can not find user "+e);
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @Override
    public ResponseEntity<?> driverHistory(String token, int id) {
        List<DriverhistoryEntity> driverhistoryList = new ArrayList<>();
        driverorderGetResponse response = new driverorderGetResponse();
        List<OrdersEntity> ordersEntity = orderRepository.findByDriverId(id);
        if(ordersEntity == null){
            response.setStatus(404);
            response.setMsg("order not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        for(OrdersEntity order:ordersEntity){
            List<DriverOrderEntity> menuList = new ArrayList<>();
            List<OrderDetailsEntity> orderDetailsEntity = orderDetailsRepository.findByOrderId(order.getId());
            for(OrderDetailsEntity orderdetail:orderDetailsEntity){
                MenusEntity menusEntity = menusRepository.findById(orderdetail.getMenuId());
                DriverOrderEntity driverOrderEntity = new DriverOrderEntity();
                driverOrderEntity.setName(menusEntity.getName());
                driverOrderEntity.setPrice(menusEntity.getPrice());
                driverOrderEntity.setQuantity(orderdetail.getQuantity());
                menuList.add(driverOrderEntity);
            }
            DriverhistoryEntity driverhistoryEntity = new DriverhistoryEntity();
            driverhistoryEntity.setCustomerId(order.getCustomerId());
            driverhistoryEntity.setCustomerLatValue(order.getCustomerLatValue());
            driverhistoryEntity.setCustomerLonValue(order.getCustomerLonValue());
            driverhistoryEntity.setDiscount(order.getDiscount());
            driverhistoryEntity.setDriverId(order.getDriverId());
            driverhistoryEntity.setDriverLatValue(order.getDriverLatValue());
            driverhistoryEntity.setDriverLonValue(order.getDriverLonValue());
            driverhistoryEntity.setFoodPrice(order.getFoodPrice());
            driverhistoryEntity.setLengthPrice(order.getLengthPrice());
            driverhistoryEntity.setMenus(menuList);
            driverhistoryEntity.setOrderDate(order.getOrderDate());
            driverhistoryEntity.setOrderRating(order.getRestaurantRating());
            driverhistoryEntity.setOrderStatus(order.getStatus());
            driverhistoryEntity.setPercentPrice(order.getPercentPrice());
            driverhistoryEntity.setQuantity(order.getQuantity());
            driverhistoryEntity.setRequiredTime(order.getRequiredTime());
            driverhistoryEntity.setRestaurantId(order.getRestaurantId());
            driverhistoryEntity.setRestaurantLatValue(order.getRestaurantLatValue());
            driverhistoryEntity.setRestaurantLonValue(order.getRestaurantLonValue());
            driverhistoryEntity.setShippedTime(order.getShippedTime());
            driverhistoryEntity.setTotalLength(order.getTotalLength());
            driverhistoryEntity.setTotalPrice(order.getTotalPrice());
            driverhistoryEntity.setTotalTime(order.getTotalTime());
            driverhistoryEntity.setUpdatedAt(order.getUpdatedAt());
            driverhistoryEntity.setWaitingTime(order.getWaitingTime());


            driverhistoryList.add(driverhistoryEntity);

        }
        response.setDriverHistory(driverhistoryList);
        response.setMsg("found driver histories");
        response.setStatus(200);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Override
    public ResponseEntity<?> orderGet(String token, int id) {
        List<DriverOrderEntity> menuList = new ArrayList<>();
        orderGetResponse response = new orderGetResponse();
        OrdersEntity ordersEntity = orderRepository.findById(id);
        if(ordersEntity == null){
            response.setStatus(404);
            response.setMsg("order not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<OrderDetailsEntity> orderDetailsEntity = orderDetailsRepository.findByOrderId(id);
        if(orderDetailsEntity == null){
            response.setStatus(404);
            response.setMsg("menuID not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        for(OrderDetailsEntity orderdetail:orderDetailsEntity){
            MenusEntity menusEntity = menusRepository.findById(orderdetail.getMenuId());
            if(menusEntity == null){
                response.setStatus(404);
                response.setMsg("menu not found!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            DriverOrderEntity driverOrderEntity = new DriverOrderEntity();
            driverOrderEntity.setName(menusEntity.getName());
            driverOrderEntity.setPrice(menusEntity.getPrice());
            driverOrderEntity.setQuantity(orderdetail.getQuantity());
            menuList.add(driverOrderEntity);

        }



        response.setCustomerId(ordersEntity.getCustomerId());
        response.setCustomerLatValue(ordersEntity.getCustomerLatValue());
        response.setCustomerLonValue(ordersEntity.getCustomerLonValue());
        response.setDiscount(ordersEntity.getDiscount());
        response.setDriverId(ordersEntity.getDriverId());
        response.setDriverLatValue(ordersEntity.getDriverLatValue());
        response.setDriverLonValue(ordersEntity.getDriverLonValue());
        response.setFoodPrice(ordersEntity.getFoodPrice());
        response.setStartPrice(ordersEntity.getStartPrice());
        response.setLengthPrice(ordersEntity.getLengthPrice());
        response.setMenus(menuList);
        response.setMsg("found order!!");
        response.setStatus(200);
        response.setOrderDate(ordersEntity.getOrderDate());
        response.setOrderRating(ordersEntity.getDriverRating());
        response.setOrderRating(ordersEntity.getRestaurantRating());
        response.setOrderStatus(ordersEntity.getStatus());
        response.setPercentPrice(ordersEntity.getPercentPrice());
        response.setQuantity(ordersEntity.getQuantity());
        response.setRequiredTime(ordersEntity.getRequiredTime());
        response.setRestaurantId(ordersEntity.getRestaurantId());
        response.setRestaurantLatValue(ordersEntity.getRestaurantLatValue());
        response.setRestaurantLonValue(ordersEntity.getRestaurantLonValue());
        response.setShippedTime(ordersEntity.getShippedTime());
        response.setTotalLength(ordersEntity.getTotalLength());
        response.setTotalPrice(ordersEntity.getTotalPrice());
        response.setTotalTime(ordersEntity.getTotalTime());
        response.setWaitingTime(ordersEntity.getWaitingTime());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> updateUser(String token, userUpdateRequest restRequest) {
        UsersEntity userEntity = userRepository.findById(restRequest.getId());
        UserInfosEntity userInfosEntity = userInfoRepository.findByUserId(restRequest.getId());
        userUpdateResponse response = new userUpdateResponse();
        try{
            //update user fcm
            if(restRequest.getFcmToken() != null){
                userEntity.setFcmToken(restRequest.getFcmToken());
                response.setMsg("Found user! Added FCM token");
                userRepository.save(userEntity);
            }
            //update driver isActive
            if(restRequest.getWorkStatus() != null){
                userEntity.setWorkStatus(restRequest.getWorkStatus());
                response.setMsg("Found user! workStatus updated");
                userRepository.save(userEntity);
            }

            // update user location
            if(restRequest.getLatValue() != null && restRequest.getLonValue() != null){
                if(userInfosEntity == null){
                    response.setStatus(404);
                    response.setMsg(" Not Found userinfo!");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                userInfosEntity.setLatValue(restRequest.getLatValue());
                userInfosEntity.setLonValue(restRequest.getLonValue());
                userInfoRepository.save(userInfosEntity);
                response.setMsg("Found user! user Location updated");
            }
            if(restRequest.getAddress() != null){
                userInfosEntity.setAddress(restRequest.getAddress());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getProvince() != null){
                userInfosEntity.setProvince(restRequest.getProvince());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getDistrict() != null){
                userInfosEntity.setDistrict(restRequest.getDistrict());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getSubdistrict() != null){
                userInfosEntity.setSubdistrict(restRequest.getSubdistrict());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getAddress2() != null){
                userInfosEntity.setAddress2(restRequest.getAddress2());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getZipcode() != null){
                userInfosEntity.setZipcode(restRequest.getZipcode());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getFirstname() != null){
                userInfosEntity.setFirstname(restRequest.getFirstname());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getLastname() != null){
                userInfosEntity.setLastname(restRequest.getLastname());
                userInfoRepository.save(userInfosEntity);
            }
            if(restRequest.getName() != null){
                userEntity.setName(restRequest.getName());
                userRepository.save(userEntity);
            }
            if(restRequest.getType() != null){
                userEntity.setType(restRequest.getType());
                userRepository.save(userEntity);
            }
            if(restRequest.getEmail() != null){
                userEntity.setEmail(restRequest.getEmail());
                userRepository.save(userEntity);
            }

            //
            //Common.LoggerInfo(userEntity);

            response.setStatus(201);
        }catch(Exception e){
            response.setStatus(400);
            response.setMsg("Can not update FCM token!"+e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Update order status
    @Override
    public ResponseEntity<?> orderUpdate(String token, OrderUpdateRequest restRequest) {
        OrdersEntity orderEntity = orderRepository.findById(restRequest.getId());
        userUpdateResponse response = new userUpdateResponse();
        try{
            orderEntity.setStatus(restRequest.getStatus());
//            orderEntity.setRequiredTime(restRequest.getRequiredTime());
//            orderEntity.setWaitingTime(restRequest.getWaitingTime());
//            orderEntity.setShippedTime(restRequest.getShippedTime());
            orderEntity.setDriverLatValue(restRequest.getDriverLatValue());
            orderEntity.setDriverLonValue(restRequest.getDriverLonValue());
            orderRepository.save(orderEntity);
            response.setStatus(201);
            response.setMsg("Update order status Successfully!");
        }catch(Exception e){
            response.setStatus(201);
            response.setMsg("Can not update FCM token!"+e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
//        OrderUpdateResponse response = new OrderUpdateResponse();
//        OrdersEntity ordersEntity = new OrdersEntity();
//        ordersEntity.setId(restRequest.getId());
//        ordersEntity.setStatus(restRequest.getStatus());
//        long millis=System.currentTimeMillis();
//        Date date = new Date(millis);
//        Time timeNow = new Time(date.getTime());
//        ordersEntity.setRequiredTime(timeNow);
//        Common.LoggerInfo(timeNow);
    }


    private ExecutorService executor = Executors.newSingleThreadExecutor();
    @Enumerated(EnumType.STRING)
    @Override
    public ResponseEntity<?> driverFind(String token,int orderId, int limit) {
        final double[] restLat = new double[1];
        final double[] restLon = new double[1];
        DriverFindResponse response = new DriverFindResponse();
        final RestaurantsEntity[] resInfo = {new RestaurantsEntity()};
        OrdersEntity ordersEntity = orderRepository.findById(orderId);
        if(ordersEntity == null){
            response.setStatus(404);
            response.setMsg("orderID not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Optional<RestaurantsEntity> restaurantsEntity = restaurantRepository.findById(ordersEntity.getRestaurantId());
        if(!restaurantsEntity.isPresent()){
            response.setStatus(404);
            response.setMsg("restaurantID not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        restaurantsEntity.ifPresent(RestaurantsEntity->{
            resInfo[0] = RestaurantsEntity;
            restLat[0] = RestaurantsEntity.getLatValue();
            restLon[0] = RestaurantsEntity.getLonValue();
            //Common.LoggerInfo(restLat[0]);
            //Common.LoggerInfo(restLon[0]);
            });

        //find driver in user table.
        final double[] driLat = new double[1];
        final double[] driLng = new double[1];
        ArrayList<String> driverList = new ArrayList<>();
        ArrayList<String> dlist1 = new ArrayList<>();
        ArrayList<String> dlist2 = new ArrayList<>();
        ArrayList<String> dlist3 = new ArrayList<>();
        double distance;
        List<UsersEntity> driversEntity = userRepository.findByType(UserType.driver);
        //find driver who is now active.
        if(driversEntity==null){ //if no active driver
            response.setStatus(404);
            response.setMsg("active driver not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        //get driver lat,lng in user_info.
        for (UsersEntity driver:driversEntity) {
            if(driver.getWorkStatus()== 1 && driver.getFcmToken() != null){
                UserInfosEntity driverInfo = userInfoRepository.findByUserId(driver.getId());
                if(driverInfo != null){
                    if(driverInfo.getLatValue() != null && driverInfo.getLonValue() != null){
                        driLat[0]=driverInfo.getLatValue();
                        driLng[0]=driverInfo.getLonValue();
                        //Common.LoggerInfo(driLat[0]);
                        //Common.LoggerInfo(driLng[0]);
                        distance = distance(restLat[0], driLat[0], restLon[0], driLng[0],0,0);
                        if(distance<=limit && ordersEntity.getCustomerId()!= driver.getId()){
                            //Common.LoggerInfo(distance);
                            if(distance<=limit/3){
                                dlist1.add(driver.getFcmToken());
                            }else if(distance > limit/3 && distance <= 2*(limit/3)){
                                dlist2.add(driver.getFcmToken());
                            }else {
                                dlist3.add(driver.getFcmToken());
                            }
                            driverList.add(driver.getFcmToken());
                        }
                    }
                }

            }
        }
        if(driverList.isEmpty()){
            int customer_id = ordersEntity.getCustomerId();
            UsersEntity userEntity = userRepository.findById(customer_id);
            try {
                //--------------(orderID,userEntity)
                return  CFCMNotification(orderId,userEntity,limit);

            } catch (JSONException e) {
                response.setStatus(404);
                response.setMsg("can't send notification to customer."+e);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }else{
            //get customer contact via phone number or email
            UserInfosEntity cusInfo = userInfoRepository.findByUserId(ordersEntity.getCustomerId());
            if(cusInfo==null){
                response.setStatus(404);
                response.setMsg("can't find customer Info.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else{

                    ArrayList<String> firebaseRes = new ArrayList<>();
                    for(int i=0;i<3;i++){
                        if(i == 0 && !dlist1.isEmpty()){
                            executor.submit(() -> {
                                try {
                                    DFCMNotification(orderId,dlist1,cusInfo,resInfo[0],0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }else if(i == 1 && !dlist2.isEmpty()){
                            executor.submit(() -> {
                                try {
                                    if(dlist1.isEmpty()){
                                        DFCMNotification(orderId,dlist2,cusInfo,resInfo[0],0);
                                    }else{
                                        DFCMNotification(orderId,dlist2,cusInfo,resInfo[0],30);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }else if(i == 2 && !dlist3.isEmpty()){
                            executor.submit(() -> {
                                try {
                                    if(dlist1.isEmpty()&&dlist2.isEmpty()){
                                        DFCMNotification(orderId,dlist3,cusInfo,resInfo[0],0);
                                    }else if(!dlist1.isEmpty()&&dlist2.isEmpty()){
                                        DFCMNotification(orderId,dlist3,cusInfo,resInfo[0],30);
                                    }else if(dlist1.isEmpty()&&!dlist2.isEmpty()) {
                                        DFCMNotification(orderId, dlist3, cusInfo, resInfo[0], 30);
                                    }else{
                                        DFCMNotification(orderId,dlist3,cusInfo,resInfo[0],60);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                    }
                    response.setList(driverList);
                    response.setListfirebaseResponse(firebaseRes);
                    response.setMsg("send message to drivers successfully");
                    response.setStatus(200);
                    return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

    }

    //fcm to driver
    public void DFCMNotification(Integer orderId, ArrayList notify_List, UserInfosEntity cusInfo, RestaurantsEntity resInfo, Integer c) throws JSONException {
        try {
            TimeUnit.SECONDS.sleep(c);
        }catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        ArrayList List = notify_List;
        JSONObject body = new JSONObject();
        body.put("registration_ids",new JSONArray(List));
        //body.put("to","/topic/all");
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "คำสั่งชื้อใหม่");
        notification.put("body", "คุณได้รับคำสั่งชื้อใหม่ ต้องการที่จะรับงานนี้หรือไม่?");

        JSONObject data = new JSONObject();
        data.put("title","การตอบรับ");
        data.put("orderid", orderId);
        data.put("cusName", cusInfo.getFirstname());
        data.put("cusPhone", cusInfo.getPhonenumber());
        data.put("cusLat", cusInfo.getLonValue());
        data.put("cusLon", cusInfo.getLonValue());
        data.put("resName", resInfo.getName());
        data.put("resPhone", resInfo.getPhonenumber());
        data.put("resLat", resInfo.getLatValue());
        data.put("resLon", resInfo.getLonValue());
        data.put("messageType", "acception");

        body.put("notification", notification);
        body.put("data", data);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(),httpHeaders);
        String result = restTemplate.postForObject(FIREBASE_API_URL,httpEntity,String.class);
        Common.LoggerInfo(result);
        //return result;
    }

    //fcm to customer
    private ResponseEntity<?> CFCMNotification(Integer orderId,UsersEntity user,Integer disLimit) throws JSONException {
        JSONObject body = new JSONObject();
        JSONObject data = new JSONObject();
        DriverFindResponse response = new DriverFindResponse();
        if(user.getFcmToken() == null){
            response.setStatus(403);
            response.setMsg("customer FCM token is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        String userList = user.getFcmToken();
        String limit = String.valueOf(disLimit/1000);
        JSONObject notification = new JSONObject();
        body.put("to",userList);
        //body.put("to","/topic/all");
        body.put("priority", "high");
        notification.put("body", "ไม่พบผู้ส่งที่ให้บริการในระยะ  "+limit+" km.");
        notification.put("title", "คุณต้องการจะเพิ่มระยะทางการคนหาหรือไม่");
        data.put("title","สั่งชื้ออีกครั้ง");
        data.put("messageType", "limit");
        data.put("limit",limit);
        data.put("orderid", orderId);
        body.put("notification",notification);
        body.put("data", data);


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(),httpHeaders);
        String result = restTemplate.postForObject(FIREBASE_API_URL,httpEntity,String.class);


//        HttpEntity<String> request = new HttpEntity<>(body.toString());
//        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
//        CompletableFuture.allOf(pushNotification).join();
        response.setStatus(200);
        response.setMsg("Driver not found in "+limit+" km.");
        response.setFirebaseResponse(result);
        return new ResponseEntity<>(response, HttpStatus.OK);

//        try {
//            response.setStatus(200);
//            String firebaseResponse = pushNotification.get();
//            response.setMsg("Driver not found in "+limit+" km.");
//            response.setFirebaseResponse(firebaseResponse);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (InterruptedException e) {
//            response.setStatus(500);
//            response.setMsg("Cant send message to customer"+e);
//            return ResponseEntity.status(500).body(response);
//        } catch (ExecutionException e) {
//            response.setStatus(400);
//            response.setMsg("Cant send message to customer"+e);
//            return ResponseEntity.status(400).body(response);
//        }
    }


    @Override
    public ResponseEntity<?> acceptionDriver(String token, AcceptionRequest restRequest){
        AcceptionResponse response = new AcceptionResponse();
        OrdersEntity orderEntity = orderRepository.findById(restRequest.getOrderId());
        if(orderEntity == null){
            response.setStatus(404);
            response.setMsg("orderID not found for acception!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(orderEntity.getDriverId()!=null){
            response.setStatus(403);
            response.setMsg("This order is already accept by another driver!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        if(orderEntity.getStatus().equals(OrderStatus.valueOf("cancel"))){
            response.setStatus(403);
            response.setMsg("This order has been canceled");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        orderEntity.setDriverId(restRequest.getUserId());
        orderRepository.save(orderEntity);
        int cusId = orderEntity.getCustomerId();
        UsersEntity usersEntity = userRepository.findById(cusId);
        UserInfosEntity userInfosEntity = userInfoRepository.findByUserId(restRequest.getUserId());
        try{
            return  CFCMNotificationAcception(restRequest.getOrderId(),usersEntity,userInfosEntity);
        }catch (Exception e){
            response.setStatus(404);
            response.setMsg("can't send driver acception notification to customer."+e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    private ResponseEntity<?> CFCMNotificationAcception(Integer orderId, UsersEntity user, UserInfosEntity userInfo) throws JSONException {
        JSONObject body = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject notification = new JSONObject();
        String userList = user.getFcmToken();
        AcceptionResponse response = new AcceptionResponse();
        body.put("to",userList);
        //body.put("to","/topic/all");
        body.put("priority", "high");
        notification.put("title", "ค้นหาผู้ส่งสำเร็จ");
        notification.put("body", "คนขับกำลังดำเนินการส่งอาหาร");

        data.put("messageType", "accepted");
        data.put("title","รายละเอียดผู้ส่ง");
        data.put("orderid", orderId);
        data.put("driverName",userInfo.getFirstname());
        data.put("driverPhone",userInfo.getPhonenumber());
        data.put("image",user.getImage());
        body.put("notification",notification);
        body.put("data", data);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(),httpHeaders);
        String result = restTemplate.postForObject(FIREBASE_API_URL,httpEntity,String.class);

//        HttpEntity<String> request = new HttpEntity<>(body.toString());
//        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
//        CompletableFuture.allOf(pushNotification).join();
        response.setStatus(200);
        response.setMsg("send notification to customer success.");
        response.setFirebaseResponse(result);
        return new ResponseEntity<>(response, HttpStatus.OK);

//        try {
//            response.setStatus(200);
//            String firebaseResponse = pushNotification.get();
//            response.setMsg("success.");
//            response.setFirebaseResponse(firebaseResponse);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (InterruptedException e) {
//            response.setStatus(500);
//            response.setMsg("Cant send message to customer"+e);
//            return ResponseEntity.status(500).body(response);
//        } catch (ExecutionException e) {
//            response.setStatus(400);
//            response.setMsg("Cant send message to customer"+e);
//            return ResponseEntity.status(400).body(response);
//        }
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * returns Distance in Meters
     */
    private static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


}