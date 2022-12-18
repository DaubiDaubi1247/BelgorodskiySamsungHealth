package com.example.reg3.repository;

import com.example.reg3.dao.TypeOfMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfMealRepository
        extends JpaRepository<TypeOfMeal, Long> {

    Optional<TypeOfMeal> findByLabel(String label);
}
