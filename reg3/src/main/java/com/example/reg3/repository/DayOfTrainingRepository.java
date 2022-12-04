package com.example.reg3.repository;

import com.example.reg3.dao.DayOfTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfTrainingRepository
        extends JpaRepository<DayOfTraining, Long> {
}
