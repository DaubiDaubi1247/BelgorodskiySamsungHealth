package com.example.reg3.repository;


import com.example.reg3.dao.Diet;
import com.example.reg3.dao.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DietRepository
        extends JpaRepository<Diet, Long> {

    Optional<Diet> findByLabel(String label);

    Optional<Diet> findDietById(Long id);

    List<Diet> findAllByStatus(String status);

    @Query("SELECT dish " +
            "FROM Diet diet " +
            "JOIN diet.dishes dish " +
            "JOIN dish.mealTimes mt " +
            "JOIN dish.type t " +
            "WHERE (?1 is null or diet.id = ?1)" +
            " AND (?3 is null or mt.label = ?3)" +
            " AND (?2 is null or t.label = ?2)")
    List<Dish> getDishes(Long idDiet, String type, String mailTime);
}
