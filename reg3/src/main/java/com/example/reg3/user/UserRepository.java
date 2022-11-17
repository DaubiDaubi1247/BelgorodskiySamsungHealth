package com.example.reg3.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<UsersOfApp, Long> {
    @Query("SELECT u FROM UsersOfApp u WHERE u.email = ?1")
    Optional<UsersOfApp> findUsertByEmail(String email);
}
