package com.example.reg3.repository;

import com.example.reg3.dao.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository
        extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByLabelAndDescription(String label, String description);
}
