package com.example.reg3.repository;

import com.example.reg3.dao.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationDataRepository
        extends JpaRepository<UserRegistrationData, Long> {
}
