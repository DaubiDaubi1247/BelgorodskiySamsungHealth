package com.example.reg3.repository;

import com.example.reg3.dao.User;
import com.example.reg3.requastion.ProgressOfUser;
import com.example.reg3.requastion.StatisticOfTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);


}
