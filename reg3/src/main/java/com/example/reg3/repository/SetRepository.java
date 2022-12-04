package com.example.reg3.repository;

import com.example.reg3.dao.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository
        extends JpaRepository<Set, Long> {
}
