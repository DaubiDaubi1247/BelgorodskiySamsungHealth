package com.example.reg3.repository;

import com.example.reg3.dao.Training;
import com.example.reg3.requastion.ProgressOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository
        extends JpaRepository<Training, Long> {

    @Query("SELECT train.id as id, " +
            " train.label as label, " +
            " train.countOfDays as countOfDays," +
            " train.description as description, " +
            " prog.dayOfTraining as dayOfTraining " +
            "FROM User u " +
            "JOIN u.userProgresInTraining prog " +
            "JOIN prog.trainingId train " +
            "WHERE u.id = ?1")
    List<ProgressOfUser> findProgressOfUser(Long id);
}
