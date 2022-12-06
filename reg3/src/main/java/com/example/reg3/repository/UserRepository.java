package com.example.reg3.repository;

import com.example.reg3.dao.User;
import com.example.reg3.requastion.ProgressOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT train.id as id, " +
            " train.label as label, " +
            " train.countOfDays as countOfDays, " +
            " prog.dayOfTraining as dayOfTraining " +
            "FROM User u " +
            "JOIN u.userProgresInTraining prog " +
            "JOIN prog.trainingId train " +
            "WHERE u.id = ?1")
    List<ProgressOfUser> findProgressOfUser(Long id);
}
