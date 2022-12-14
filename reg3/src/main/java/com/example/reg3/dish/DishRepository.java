package com.example.reg3.dish;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.diet.Diet;
import com.example.reg3.diet.DietRepository;
import com.example.reg3.meal.MealTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository
        extends JpaRepository<Dish, Long> {


    Optional<Dish> findByLabel(String label);


}
