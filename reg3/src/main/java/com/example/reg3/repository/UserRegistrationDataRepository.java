package com.example.reg3.repository;

import com.example.reg3.dao.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRegistrationDataRepository
        extends JpaRepository<UserRegistrationData, Long> {


    Optional<UserRegistrationData> findUserRegistrationDataByEmail(String email);
}
///gsfdhgwthwet