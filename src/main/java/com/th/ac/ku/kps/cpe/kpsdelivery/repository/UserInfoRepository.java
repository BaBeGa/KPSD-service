package com.th.ac.ku.kps.cpe.kpsdelivery.repository;


import com.th.ac.ku.kps.cpe.kpsdelivery.model.UserInfosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserInfoRepository extends JpaRepository<UserInfosEntity, Integer> {
    UserInfosEntity findByUserId (Integer id_user);
}
