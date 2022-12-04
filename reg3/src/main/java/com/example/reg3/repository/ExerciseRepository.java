package com.example.reg3.repository;

import com.example.reg3.dao.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository
        extends JpaRepository<Exercise, Long> {
}
