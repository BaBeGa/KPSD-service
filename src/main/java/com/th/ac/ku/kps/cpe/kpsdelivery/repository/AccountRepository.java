package com.th.ac.ku.kps.cpe.kpsdelivery.repository;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository< AccountsEntity, Integer> {
    AccountsEntity findByUserId (int user_id);
}
