package com.th.ac.ku.kps.cpe.kpsdelivery.service;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.*;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;
//import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.create.OrderCreateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.get.userGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.RestaurantRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.UserInfoRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.OrderRepository;
import com.th.ac.ku.kps.cpe.kpsdelivery.unity.Common;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    private static PushNotificationsService pushNotificationsService;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    public UserServiceImpl(PushNotificationsService pushNotificationsService,UserRepository userRepository, UserInfoRepository userInfoRepository, OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.pushNotificationsService = pushNotificationsService;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //start here!!!
    @Override
    public ResponseEntity<?> getUser (String token, int id) {
        UsersEntity userEntity = userRepository.findById(id);
        userGetResponse response = new userGetResponse();
        try{
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
    public ResponseEntity<?> updateUser(String token, userUpdateRequest restRequest) {
        UsersEntity userEntity = userRepository.findById(restRequest.getId());
        userUpdateResponse response = new userUpdateResponse();
        try{
            userEntity.setFcmToken(restRequest.getFcmToken());
            Common.LoggerInfo(userEntity);
            userRepository.save(userEntity);
            response.setStatus(201);
            response.setMsg("Found user! Added FCM token");
        }catch(Exception e){
            response.setStatus(201);
            response.setMsg("Can not update FCM token!"+e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @Override
//    public ResponseEntity<?> orderCreate(String token, OrderCreateBodyRequest restRequest) {
//
//        OrderCreateResponse response = new OrderCreateResponse();
//        //List<OrdersEntity> orderEntity = orderRepository.
//        //restRequest.getId();//got id order
//        OrdersEntity ordersEntity = new OrdersEntity();
//        //in case insert data. Order's ID not require.
//        //in case update data. Order's ID require.
//        ordersEntity.setCustomerId(restRequest.getCustomerId());//this line set the cus ID to the ordersEntity
//        ordersEntity.setRestaurantId(restRequest.getRestaurantID());
//        ordersEntity.setStatus(restRequest.getStatus());
//        long millis=System.currentTimeMillis();
//        Date date = new Date(millis);
//        ordersEntity.setOrderDate(date);
//        orderRepository.save(ordersEntity);
//
//        //Common.LoggerInfo(ordersEntity);
//        response.setStatus(200);
//        response.setMsg("Successful");
//        response.setId_order(ordersEntity.getId());
//        return ResponseEntity.ok(response);
//
//    }
    @Override
    public ResponseEntity<?> orderUpdate(String token, OrderUpdateRequest restRequest) {
        OrdersEntity orderEntity = orderRepository.findById(restRequest.getId());
        userUpdateResponse response = new userUpdateResponse();
        try{
            orderEntity.setDriverId(restRequest.getDriverId());
            orderEntity.setStatus(restRequest.getStatus());
//            orderEntity.setRequiredTime(restRequest.getRequiredTime());
//            orderEntity.setWaitingTime(restRequest.getWaitingTime());
//            orderEntity.setShippedTime(restRequest.getShippedTime());
            orderEntity.setDriverLatValue(restRequest.getDriverLatValue());
            orderEntity.setDriverLonValue(restRequest.getDriverLonValue());
            orderRepository.save(orderEntity);
            response.setStatus(201);
            response.setMsg("Update order Successfully!");
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

    @Enumerated(EnumType.STRING)
    @Override
    public ResponseEntity<?> driverFind(String token, DriverFindRequest restRequest) {
        final double[] restLat = new double[1];
        final double[] restLon = new double[1];
        DriverFindResponse response = new DriverFindResponse();
        final RestaurantsEntity[] resInfo = {new RestaurantsEntity()};
        Optional<RestaurantsEntity> restaurantsEntity = restaurantRepository.findById(restRequest.getRestaurantID());
        if(restaurantsEntity == null){
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
        ArrayList driverList = new ArrayList();
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
            if(driver.getIsActiveAccount()== 1){
                UserInfosEntity driverInfo = userInfoRepository.findByUserId(driver.getId());
                if(driverInfo==null){
                    response.setStatus(404);
                    response.setMsg("can't find userInfo. Please check UserInfo in database");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }

                driLat[0]=driverInfo.getLatValue();
                driLng[0]=driverInfo.getLonValue();
                Common.LoggerInfo(driLat[0]);
                Common.LoggerInfo(driLng[0]);

                distance = distance(restLat[0], driLat[0], restLon[0], driLng[0],0,0);
                if(distance<=restRequest.getDislimit()){
                    Common.LoggerInfo(distance);
                    driverList.add(driver.getFcmToken());
                }

            }
        }
        if(driverList.isEmpty()){
            int customer_id = restRequest.getCustomerId();
            UsersEntity userEntity = userRepository.findById(customer_id);
            try {
                //--------------(orderID,userEntity)
                return  CFCMNotification(restRequest.getId(),userEntity,restRequest.getDislimit());

            } catch (JSONException e) {
                response.setStatus(404);
                response.setMsg("can't send notification to customer."+e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }else{
            //get customer contact via phone number or email
            UserInfosEntity cusInfo = userInfoRepository.findByUserId(restRequest.getCustomerId());
            if(cusInfo==null){
                response.setStatus(404);
                response.setMsg("can't find customer Info.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }else{
                try {
                    return DFCMNotification(restRequest.getId(),driverList,cusInfo,resInfo[0]);
                } catch (JSONException e) {
                    response.setStatus(404);
                    response.setMsg("can't send notification to driver."+e);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
        }

    }
    //fcm to driver
    public ResponseEntity<?> DFCMNotification(Integer orderId,ArrayList notify_List,UserInfosEntity cusInfo,RestaurantsEntity resInfo) throws JSONException {
        ArrayList List = notify_List;
        JSONObject body = new JSONObject();
        DriverFindResponse response = new DriverFindResponse();
        body.put("registration_ids",new JSONArray(List));
        //body.put("to","/topic/all");
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "New order available.");
        notification.put("body", "You have a new order");

        JSONObject data = new JSONObject();
        data.put("orderid", orderId);
        data.put("cuslat", cusInfo.getLatValue());
        data.put("cuslon", cusInfo.getLonValue());
        data.put("cusphone", cusInfo.getPhonenumber());
        data.put("reslat", resInfo.getLatValue());
        data.put("reslon", resInfo.getLonValue());
        data.put("resphone", resInfo.getPhonenumber());

        body.put("notification", notification);
        body.put("data", data);
        /*
         {
         "notification": {
         "title": "JSA Notification",
         "body": "Happy Message!"
         },
         "data": {
         "Key-1": "JSA Data 1",
         "Key-2": "JSA Data 2"
         },
         "to": "/topics/JavaSampleApproach",
         "priority": "high"
         }
         */
        HttpEntity<String> request = new HttpEntity<>(body.toString());
        Common.LoggerInfo(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            response.setStatus(200);
            String firebaseResponse = pushNotification.get();
            response.setMsg("Send massage to driver successful");
            response.setFirebaseResponse(firebaseResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InterruptedException e) {
            response.setStatus(500);
            response.setMsg("Cant send message to driver.."+e);
            return ResponseEntity.status(404).body(response);
        } catch (ExecutionException e) {
            response.setStatus(500);
            response.setMsg("Cant send message to driver.."+e);
            return ResponseEntity.status(404).body(response);
        }

    }
    //fcm to customer
    public ResponseEntity<?> CFCMNotification(Integer orderId,UsersEntity user,Integer disLimit) throws JSONException {
        JSONObject body = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject notification = new JSONObject();
        ArrayList userList = new ArrayList();
        userList.add(user.getFcmToken());
        DriverFindResponse response = new DriverFindResponse();
        body.put("registration_ids",new JSONArray(userList));
        //body.put("to","/topic/all");
        body.put("priority", "high");
        notification.put("title", "No driver available near the restaurant, "+disLimit+" km.");
        notification.put("body", "Do you want to increase your distance limit?");
        data.put("orderid", orderId);
        body.put("notification",notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            response.setStatus(200);
            String firebaseResponse = pushNotification.get();
            response.setMsg("Driver not found in "+disLimit+" km.");
            response.setFirebaseResponse(firebaseResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InterruptedException e) {
            response.setStatus(500);
            response.setMsg("Cant send message to customer"+e);
            return ResponseEntity.status(500).body(response);
        } catch (ExecutionException e) {
            response.setStatus(400);
            response.setMsg("Cant send message to customer"+e);
            return ResponseEntity.status(400).body(response);
        }
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