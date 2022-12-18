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

    List<Diet> findAllByStatus(String status);

    @Query("SELECT dish " +
            "FROM Diet diet " +
            "JOIN diet.dishes dish " +
            "JOIN dish.mealTimes mt " +
            "JOIN dish.type t " +
            "WHERE (:idDiet is null or diet.id = :idDiet)" +
            " AND (:mailTime is null or mt.label = :mailTime)" +
            " AND (:type is null or t.label = :type)")
    List<Dish> getDishes(Long idDiet, String type, String mailTime);
}
