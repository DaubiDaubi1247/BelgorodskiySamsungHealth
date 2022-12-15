package com.example.reg3.typeOfMeal;

import com.example.reg3.dish.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfMealRepository
        extends JpaRepository<TypeOfMeal, Long> {

    Optional<TypeOfMeal> findByLabel(String label);
}
