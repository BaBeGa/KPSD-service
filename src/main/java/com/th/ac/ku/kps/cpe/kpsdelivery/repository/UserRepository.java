package com.th.ac.ku.kps.cpe.kpsdelivery.repository;


import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

import com.th.ac.ku.kps.cpe.kpsdelivery.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findById(int user_id);
    List<UsersEntity> findByType(UserType user_type);
}
