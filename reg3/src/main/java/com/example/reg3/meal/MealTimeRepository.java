package com.example.reg3.meal;


import com.example.reg3.diet.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealTimeRepository
        extends JpaRepository<MealTime, Long> {



    Optional<MealTime> findMealTimeByLabel(String label);

}
