package com.th.ac.ku.kps.cpe.kpsdelivery.service;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.*;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;
//import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.create.OrderCreateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update.OrderUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception.AcceptionRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception.AcceptionResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.DriverFindResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.get.userGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.get.orderGetResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateResponse;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update.userUpdateRequest;
import com.th.ac.ku.kps.cpe.kpsdelivery.repository.*;
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
    private final AccountRepository accountRepository;
    private final UserInfoRepository userInfoRepository;
    private final MenusRepository menusRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final RestaurantRepository restaurantRepository;
    public UserServiceImpl(PushNotificationsService pushNotificationsService, UserRepository userRepository, AccountRepository accountRepository, UserInfoRepository userInfoRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, MenusRepository menusRepository, RestaurantRepository restaurantRepository) {
        this.pushNotificationsService = pushNotificationsService;
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
    public ResponseEntity<?> orderGet(String token, int id) {
        ArrayList<MenusEntity> menuList = new ArrayList();
        orderGetResponse response = new orderGetResponse();
        OrdersEntity ordersEntity = orderRepository.findById(id);
        if(ordersEntity == null){
            response.setStatus(404);
            response.setMsg("orderID not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(ordersEntity != null){
            List<OrderDetailsEntity> orderDetailsEntity = orderDetailsRepository.findByOrderId(id);
            if(orderDetailsEntity == null){
                response.setStatus(404);
                response.setMsg("menuID not found!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }else if(orderDetailsEntity != null){
                for(OrderDetailsEntity orderdetail:orderDetailsEntity){
                    MenusEntity menusEntity = menusRepository.findById(orderdetail.getMenuId());
                    if(menusEntity == null){
                        response.setStatus(404);
                        response.setMsg("menu not found!");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }else if(menusEntity != null){
                        menuList.add(menusEntity);
                    }
                }
            }
        }

        response.setCustomerId(ordersEntity.getCustomerId());
        response.setCustomerLatValue(ordersEntity.getCustomerLatValue());
        response.setCustomerLonValue(ordersEntity.getRestaurantLonValue());
        response.setDiscount(ordersEntity.getDiscount());
        response.setDriverId(ordersEntity.getDriverId());
        response.setDriverLatValue(ordersEntity.getDriverLatValue());
        response.setDriverLonValue(ordersEntity.getDriverLonValue());
        response.setFoodPrice(ordersEntity.getFoodPrice());
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
        userUpdateResponse response = new userUpdateResponse();
        try{
            //update user fcm
            if(restRequest.getFcmToken() != null){
                userEntity.setFcmToken(restRequest.getFcmToken());
                response.setMsg("Found user! Added FCM token");
            }
            //update driver isActive
            if(restRequest.getIsActiveAccount() != null){
                userEntity.setIsActiveAccount(restRequest.getIsActiveAccount());
                response.setMsg("Found user! IsActiveAccount updated");
            }
            // update user location
            if(restRequest.getLatValue() != null && restRequest.getLonValue() != null){
                UserInfosEntity userInfosEntity = userInfoRepository.findByUserId(restRequest.getId());
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
            Common.LoggerInfo(userEntity);
            userRepository.save(userEntity);
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
        ArrayList<String> driverList = new ArrayList();
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
            if(driver.getIsActiveAccount()== 1 && driver.getFcmToken() != null){
                UserInfosEntity driverInfo = userInfoRepository.findByUserId(driver.getId());
                if(driverInfo==null){
                    response.setStatus(404);
                    response.setMsg("can't find userInfo. Please check UserInfo in database");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
                if(driverInfo.getLatValue() != null && driverInfo.getLonValue() != null){
                    driLat[0]=driverInfo.getLatValue();
                    driLng[0]=driverInfo.getLonValue();
                    Common.LoggerInfo(driLat[0]);
                    Common.LoggerInfo(driLng[0]);
                    distance = distance(restLat[0], driLat[0], restLon[0], driLng[0],0,0);
                    if(distance<=limit){
                        Common.LoggerInfo(distance);
                        driverList.add(driver.getFcmToken());
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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }else{
            //get customer contact via phone number or email
            UserInfosEntity cusInfo = userInfoRepository.findByUserId(ordersEntity.getCustomerId());
            if(cusInfo==null){
                response.setStatus(404);
                response.setMsg("can't find customer Info.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }else{
                try {
                    return DFCMNotification(orderId,driverList,cusInfo,resInfo[0]);
                } catch (JSONException e) {
                    response.setStatus(404);
                    response.setMsg("can't send notification to driver."+e);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
        }

    }
    //fcm to driver
    private ResponseEntity<?> DFCMNotification(Integer orderId,ArrayList notify_List,UserInfosEntity cusInfo,RestaurantsEntity resInfo) throws JSONException {
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
        data.put("messageType", "acception");

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
        String limit = String.valueOf(disLimit);
        JSONObject notification = new JSONObject();
        body.put("to",userList);
        //body.put("to","/topic/all");
        body.put("priority", "high");
        notification.put("title", "No driver available near the restaurant, "+limit+" km.");
        notification.put("body", "Do you want to increase your distance limit?");
        data.put("messageType", "limit");
        data.put("orderid", orderId);
        body.put("notification",notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            response.setStatus(200);
            String firebaseResponse = pushNotification.get();
            response.setMsg("Driver not found in "+limit+" km.");
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
    public ResponseEntity<?> acceptionDriver(String token, AcceptionRequest restRequest){
        AcceptionResponse response = new AcceptionResponse();
        OrdersEntity orderEntity = orderRepository.findById(restRequest.getOrderId());
        if(orderEntity == null){
            response.setStatus(404);
            response.setMsg("orderID not found for acception!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if(orderEntity.getDriverId()!=null){
            response.setStatus(403);
            response.setMsg("This order is already accept by another driver!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        if(orderEntity.getStatus().equals("cancel")){
            response.setStatus(403);
            response.setMsg("This order has been canceled");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        orderEntity.setDriverId(restRequest.getUserId());
        orderRepository.save(orderEntity);
        UsersEntity usersEntity = userRepository.findById(restRequest.getUserId());
        try{
            return  CFCMNotificationAcception(restRequest.getOrderId(),usersEntity);
        }catch (Exception e){
            response.setStatus(404);
            response.setMsg("can't send driver acception notification to customer."+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    private ResponseEntity<?> CFCMNotificationAcception(Integer orderId, UsersEntity user) throws JSONException {
        JSONObject body = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject notification = new JSONObject();
        String userList = user.getFcmToken();
        DriverFindResponse response = new DriverFindResponse();
        body.put("to",userList);
        //body.put("to","/topic/all");
        body.put("priority", "high");
        notification.put("title", "ค้นหาคนขับสำเร็จ");
        notification.put("body", "คนขับกำลังทำการส่งอาหารให้คุณ");

        data.put("messageType", "accepted");
        data.put("orderid", orderId);
        body.put("notification",notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            response.setStatus(200);
            String firebaseResponse = pushNotification.get();
            response.setMsg("success.");
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