package com.example.reg3.repository;

import com.example.reg3.dao.DayOfTraining;
import com.example.reg3.dao.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOfTrainingRepository
        extends JpaRepository<DayOfTraining, Long> {


}
