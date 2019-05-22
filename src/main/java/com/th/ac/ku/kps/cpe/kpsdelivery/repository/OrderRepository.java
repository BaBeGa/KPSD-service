package com.th.ac.ku.kps.cpe.kpsdelivery.repository;


import com.th.ac.ku.kps.cpe.kpsdelivery.model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersEntity, Integer> {
    OrdersEntity findById(int order_id);
}
