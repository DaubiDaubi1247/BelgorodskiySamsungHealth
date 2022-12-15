package com.example.reg3.diet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DietRepository
        extends JpaRepository<Diet, Long> {


    Optional<Diet> findByLabel(String labek);


    List<Diet> findAllByStatus(String status);
}
