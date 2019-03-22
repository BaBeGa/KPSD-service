package com.th.ac.ku.kps.cpe.kpsdelivery.repository;


import com.th.ac.ku.kps.cpe.kpsdelivery.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByIdUser(Integer id_user);
}
