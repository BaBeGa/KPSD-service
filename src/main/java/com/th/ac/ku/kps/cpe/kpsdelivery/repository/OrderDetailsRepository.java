package com.th.ac.ku.kps.cpe.kpsdelivery.repository;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Integer> {
    List<OrderDetailsEntity> findByOrderId (int order_id);
}
