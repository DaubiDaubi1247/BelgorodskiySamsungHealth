package com.example.reg3.Service;


import com.example.reg3.dao.Set;
import com.example.reg3.dao.Training;
import com.example.reg3.repository.DayOfTrainingRepository;
import com.example.reg3.repository.ExerciseRepository;
import com.example.reg3.repository.SetRepository;
import com.example.reg3.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final SetRepository setRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;

    private final DayOfTrainingRepository dayOfTrainingRepository;


    @Autowired
    public TrainingService(SetRepository setRepository,
                           ExerciseRepository exerciseRepository,
                           TrainingRepository trainingRepository,
                           DayOfTrainingRepository dayOfTrainingRepository) {
        this.setRepository = setRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
        this.dayOfTrainingRepository = dayOfTrainingRepository;
    }

    public ResponseEntity<Object> getTrainingDays(Long trainId) {
        var trainingDays = trainingRepository.findDaysOfTraining(trainId);

        if (trainingDays.size() == 0) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("в данной тренеровки отсутствуют дни");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(trainingDays);
        }
    }

    public ResponseEntity<Object> getTrainings() {

        var trainingListings = trainingRepository.findByStatus("available");

        if (trainingListings.size() == 0) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("тренеровки отсутствуют в бд");
        }
        else {
            for (var train : trainingListings) {
                train.setDaysOfTraining(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(trainingListings);
        }
    }


    public ResponseEntity<Object> addTrain(Training train) {
        var questionTrain = trainingRepository.findByLabel(train.getLabel());

        if (!questionTrain.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Данное название тренеровки уже занято");
        }
        if (train.getCountOfDays() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("У тренеровки отрицательное колличество дней");
        }
        eliminationCollisionInDaysOfTraining(train);
        train.setCountOfDays(train.getDaysOfTraining().size());
        try{
            trainingRepository.save(train);
            return ResponseEntity.status(HttpStatus.OK).body("тренеровка успешно добавлена");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка сохранения "
                    + e.getMessage());
        }
    }

    private void eliminationCollisionInDaysOfTraining(Training training) {
        var daysOfTraining = training.getDaysOfTraining();

        for (var dayOfTraining : daysOfTraining) {
            var sets = dayOfTraining.getSets();
            List<Set> newSet = new ArrayList<>();
            for (Set set : sets) {
                eliminationCollisionInSet(set);
                var findSet = setRepository.findSetByNumberOfRepetitionsAndRestTimeInSecAndExercise
                        (set.getNumberOfRepetitions(), set.getRestTimeInSec(), set.getExercise());

                if (findSet.isEmpty()) {
                    set = setRepository.save(set);
                } else {
                    set = findSet.get();
                }
                newSet.add(set);
            }
            dayOfTraining.setSets(newSet);
        }
        daysOfTraining = dayOfTrainingRepository.saveAll(daysOfTraining);
    }

    private void eliminationCollisionInSet(Set set) {
        var exercise = set.getExercise();

        var findingEx = exerciseRepository.findByLabelAndDescription(exercise.getLabel(), exercise.getDescription());

        if (findingEx.isEmpty()) {
            exercise = exerciseRepository.save(exercise);
        } else {
            exercise = findingEx.get();
        }
        set.setExercise(exercise);
    }



    public ResponseEntity<Object> getTrainingDay(Long trainId, Integer numOfDay) {
        var trainingDay = trainingRepository.findDayOfTrain(trainId, numOfDay);

        if (trainingDay == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("отствует данный день тренеровки");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(trainingDay);
        }
    }
}
