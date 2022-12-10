package com.example.reg3.repository;

import com.example.reg3.dao.Exercise;
import com.example.reg3.dao.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepository
        extends JpaRepository<Set, Long> {
    Optional<Set> findSetByNumberOfRepetitionsAndRestTimeInSecAndExercise
            (Integer numberOfRepetitions, Integer restTimeInSec, Exercise exercise);

}
