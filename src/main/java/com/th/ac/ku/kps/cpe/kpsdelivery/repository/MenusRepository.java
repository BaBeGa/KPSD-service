package com.th.ac.ku.kps.cpe.kpsdelivery.repository;


import com.th.ac.ku.kps.cpe.kpsdelivery.model.MenusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenusRepository extends JpaRepository< MenusEntity, Integer> {
    MenusEntity findById (int menu_id);
}
