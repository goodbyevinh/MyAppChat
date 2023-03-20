package com.vinh.chat.repository;


import com.vinh.chat.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findByEmail(String email);

}
