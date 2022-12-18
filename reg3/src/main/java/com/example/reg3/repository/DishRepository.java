package com.example.reg3.repository;


import com.example.reg3.dao.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository
        extends JpaRepository<Dish, Long> {


    Optional<Dish> findByLabel(String label);


}
