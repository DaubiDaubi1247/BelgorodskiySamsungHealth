package com.example.reg3.Service;


import com.example.reg3.LogBot.TelegramBot;
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
    @Autowired
    TelegramBot bot;
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
        bot.sendInfo("запрос всех дней тренеровоки с id " + trainId);
        var trainingDays = trainingRepository.findDaysOfTraining(trainId);


        if (trainingDays.size() == 0) {
            bot.sendWarning("в тренеровоке с id " + trainId + " отсутствуют дни");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("в данной тренеровки отсутствуют дни");
        } else {
            bot.sendWarning("из тренеровоке с id " + trainId + " возвращено " +trainingDays.size() + " дней");
            return ResponseEntity.status(HttpStatus.OK).body(trainingDays);
        }
    }

    public ResponseEntity<Object> getTrainings() {
        bot.sendInfo("запрос всех активных тренеровок из БД");
        var trainingListings = trainingRepository.findByStatus("available");

        if (trainingListings.size() == 0) {
            bot.sendWarning("активные тренеровки отсутствуют в бд");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("тренеровки отсутствуют в бд");
        } else {
            bot.sendInfo("Возврат всех активных тренеровок с активным статусом из БД");
            for (var train : trainingListings) {
                train.setDaysOfTrainings(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(trainingListings);
        }
    }

    public ResponseEntity<Object> getAllTrainings() {
        bot.sendInfo("запрос всех тренеровок из БД");
        var trainingListings = trainingRepository.findAll();

        if (trainingListings.size() == 0) {
            bot.sendWarning("тренеровки отсутствуют в бд");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("тренеровки отсутствуют в бд");
        } else {
            for (var train : trainingListings) {
                train.setDaysOfTrainings(null);
            }
            bot.sendInfo("Возврат тренеровок со всем статусам из БД");
            return ResponseEntity.status(HttpStatus.OK).body(trainingListings);
        }
    }


    public ResponseEntity<Object> addTrain(Training train) {
        bot.sendInfo("проверка наличия тренеровки с названием " + train.getLabel() + " "
                + train.getId() + "id в БД");
        var questionTrain = trainingRepository.findByLabel(train.getLabel());

        if (!questionTrain.isEmpty()) {
            bot.sendWarning("тренеровки с названием " + questionTrain.get(0).getLabel() + " "
                    + questionTrain.get(0).getId() + "id уже есть  в БД");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Данное название тренеровки уже занято");
        }
        if (train.getCountOfDays() < 0) {
            bot.sendError("тренеровки с названием " + questionTrain.get(0).getLabel() + " "
                    + questionTrain.get(0).getId() + "id имеет отрицательно колличество дней");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("У тренеровки отрицательное колличество дней");
        }
        eliminationCollisionInDaysOfTraining(train);
        train.setCountOfDays(train.getDaysOfTrainings().size());
        try {
            if (train.getStatus() == null) train.setStatus("available");
            trainingRepository.save(train);
            bot.sendInfo("тренеровки с названием " + train.getLabel() +
                    " успешно добавлена");
            return ResponseEntity.status(HttpStatus.OK).body("тренеровка успешно добавлена");
        } catch (Exception e) {
            bot.sendInfo("тренеровки с названием " + train.getLabel() +
                    " не была сохранена\n"+ "Сообщение ошибки: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка сохранения "
                    + e.getMessage());
        }
    }

    private void eliminationCollisionInDaysOfTraining(Training training) {
        var daysOfTraining = training.getDaysOfTrainings();
        bot.sendInfo("Проверка колизии тренеровки" + training.getLabel() + " c id" + training.getId());

        for (var dayOfTraining : daysOfTraining) {
            bot.sendInfo("Проверка колизии дня тренеровки "
                    + dayOfTraining.getNumberOfDay() + " c id" + dayOfTraining.getId());

            var sets = dayOfTraining.getSets();
            List<Set> newSet = new ArrayList<>();
            for (Set set : sets) {
                eliminationCollisionInSet(set);
                bot.sendInfo("Запрос наличия подхода " + set.getNumberOfRepetitions() + " " + set.getExercise().getLabel());
                var findSet = setRepository.findSetByNumberOfRepetitionsAndRestTimeInSecAndExercise
                        (set.getNumberOfRepetitions(), set.getRestTimeInSec(), set.getExercise());

                if (findSet.isEmpty()) {
                    bot.sendInfo("подход " + set.getNumberOfRepetitions() + " "
                            + set.getExercise().getLabel() + "отсутствует в БД");
                    set = setRepository.save(set);
                    bot.sendInfo("подход " + set.getNumberOfRepetitions() + " "
                            + set.getExercise().getLabel() + "добавлен в БД");
                } else {
                    bot.sendInfo("подход " + set.getNumberOfRepetitions() + " "
                            + set.getExercise().getLabel() + "есть  в БД");
                    set = findSet.get();
                }
                newSet.add(set);
            }
            dayOfTraining.setSets(newSet);
        }
        daysOfTraining = dayOfTrainingRepository.saveAll(daysOfTraining);
    }

    private void eliminationCollisionInSet(Set set) {
        bot.sendInfo("Проверка колизии подхода  " +
                " c id" + set.getId());
        var exercise = set.getExercise();
        bot.sendInfo("Проверка колизии упражнения" + exercise.getLabel() + " c id" + exercise.getId());

        var findingEx = exerciseRepository.findByLabelAndDescription(exercise.getLabel(), exercise.getDescription());

        if (findingEx.isEmpty()) {
            bot.sendInfo(" упражнения " + exercise.getLabel() +  " отсутствует в бд");
            exercise = exerciseRepository.save(exercise);
            bot.sendInfo(" упражнения " + exercise.getLabel() + " c id" + exercise.getId() + " добавлено в бд");

        } else {
            bot.sendInfo(" упражнения " + exercise.getLabel() + " c id" + exercise.getId() + " есть в бд");
            exercise = findingEx.get();
        }
        set.setExercise(exercise);
    }


    public ResponseEntity<Object> getTrainingDay(Long trainId, Integer numOfDay) {
        bot.sendInfo("Запрос на тренеровку с id" + trainId + " номеру дня " + numOfDay);
        var trainingDay = trainingRepository.findDayOfTrain(trainId, numOfDay);

        if (trainingDay == null) {
            bot.sendError("отствует  день тренеровки с id трененовки" + trainId + " номер дня " + numOfDay);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("отствует данный день тренеровки");
        } else {
            bot.sendInfo("возвращен  день тренеровки с id трененовки" + trainId + " номер дня " + numOfDay);
            return ResponseEntity.status(HttpStatus.OK).body(trainingDay);
        }
    }

    public ResponseEntity<Object> deactivateTrain(Long trainId) {
        try {
            bot.sendInfo("Запро на тренеровку с id " + trainId);
            var train = trainingRepository.getReferenceById(trainId);
            var status = train.getStatus();
            if ("not available".equals(status)) {
                bot.sendInfo("Тренеровка " +train.getLabel() +" с " + trainId + " id уже и так не доступна");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Тренеровка с " + trainId + " id уже и так не доступна");
            } else {
                train.setStatus("not available");
                trainingRepository.save(train);
                bot.sendInfo("Тренеровка " +train.getLabel() +" с " + trainId + " id диактивированна");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Тренеровка с " + trainId + " id теперь не доступна");
            }
        } catch (Exception e) {
            bot.sendError("Тренеровка  с " + trainId + " id не найдена");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Тренеровка с " + trainId + " id не найдена");
        }
    }

    public ResponseEntity<Object> activeTrain(Long trainId) {
        try {
            var train = trainingRepository.getReferenceById(trainId);
            var status = train.getStatus();
            if ("available".equals(status)) {
                bot.sendInfo("Тренеровка " +train.getLabel() +" с " + trainId + " id уже и так доступна");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Тренеровка с " + trainId + " id уже и так доступна");
            } else {
                train.setStatus("available");
                trainingRepository.save(train);
                bot.sendInfo("Тренеровка " +train.getLabel() +" с " + trainId + " id тепнрь активна");

                return ResponseEntity.status(HttpStatus.OK)
                        .body("Тренеровка с " + trainId + " id теперь доступна");
            }
        } catch (Exception e) {
            bot.sendError("Тренеровка  с " + trainId + " id не найдена");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Тренеровка с " + trainId + " id не найдена");
        }
    }
}


