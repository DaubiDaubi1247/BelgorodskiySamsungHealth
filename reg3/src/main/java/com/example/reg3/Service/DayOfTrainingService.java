package com.example.reg3.Service;

import com.example.reg3.repository.DayOfTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DayOfTrainingService {

    private final DayOfTrainingRepository dayOfTrainingRepository;

    @Autowired
    public DayOfTrainingService(DayOfTrainingRepository dayOfTrainingRepository) {
        this.dayOfTrainingRepository = dayOfTrainingRepository;
    }


//    public ResponseEntity<Object> getTrainingDays(Long trainId) {
//        var trainingDays = dayOfTrainingRepository.findDayOfTrainingByTrainingId(trainId);
//
//        if (trainingDays.size() == 0) {
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("в данной тренеровки отсутствуют дни");
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.OK).body(trainingDays);
//        }
//    }
//
//    public ResponseEntity<Object> getTrainingDay(Long trainId, Integer numOfDay) {
//        var trainingDay = dayOfTrainingRepository.
//                findDayOfTrainingByTrainingIdAndNumberOfDay(trainId, numOfDay);
//
//        if (trainingDay == null) {
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("отствует данный день тренеровки");
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.OK).body(trainingDay);
//        }
//    }

}
