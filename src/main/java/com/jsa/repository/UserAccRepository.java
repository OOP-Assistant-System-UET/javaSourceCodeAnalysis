package com.jsa.repository;

import com.jsa.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUserName(String userName);
}
