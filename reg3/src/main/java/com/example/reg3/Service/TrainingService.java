package com.example.reg3.Service;


import com.example.reg3.controller.TrainingController;
import com.example.reg3.dao.Training;
import com.example.reg3.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;


    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public ResponseEntity<Object> getTrainings() {

        var trainingListings = trainingRepository.findByStatus("available");

        if (trainingListings.size() == 0) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("тренеровки отсутствуют в бд");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(trainingListings);
        }
    }

//    public ResponseEntity<Object> addTrain(Training train) {
//        var questionTrain = trainingRepository.findByLabel(train.getLabel());
//
//        if (!questionTrain.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Данное название тренеровки уже занято");
//        }
//
//    }
}
