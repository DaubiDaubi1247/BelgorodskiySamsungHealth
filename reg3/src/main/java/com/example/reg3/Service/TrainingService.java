package com.example.reg3.Service;


import com.example.reg3.dao.Training;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.requastion.TrainingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;


    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public ResponseEntity<Object> getTrainings() {

        var trainingListings = trainingRepository.findAll();

        if (trainingListings.size() == 0) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("тренеровки отсутствуют в бд");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(trainingListings);
        }

    }

}
