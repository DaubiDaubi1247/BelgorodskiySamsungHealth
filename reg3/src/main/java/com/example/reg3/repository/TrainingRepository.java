package com.example.reg3.repository;

import com.example.reg3.dao.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository
        extends JpaRepository<Training, Long> {
}
