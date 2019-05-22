package com.th.ac.ku.kps.cpe.kpsdelivery.repository;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.RestaurantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantsEntity, Integer> {

}
