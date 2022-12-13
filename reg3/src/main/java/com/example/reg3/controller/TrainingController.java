package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.DayOfTrainingService;
import com.example.reg3.Service.TrainingService;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("training")
public class TrainingController {
    @Autowired
    TelegramBot bot;

    private final TrainingService trainingService;

    private final DayOfTrainingService dayOfTrainingService;

    private final UserService userService;

    @Autowired
    public TrainingController(TrainingService trainingService,
                              DayOfTrainingService dayOfTraining,
                              UserService userService) {
        this.trainingService = trainingService;
        this.dayOfTrainingService = dayOfTraining;
        this.userService = userService;
    }

    @GetMapping("deactivate")
    public ResponseEntity<Object> deactivateTrain(@RequestParam(required = false) Long trainId) {
        try {

            bot.sendInfo("обращение к training/deactivate");
            var res = trainingService.deactivateTrain(trainId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("active")
    public ResponseEntity<Object> activeTrain(@RequestParam(required = false) Long trainId) {
        try {

            bot.sendInfo("обращение к training/active");
            var res = trainingService.activeTrain(trainId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("LightBackground")
    public ResponseEntity<Object> getLightBackground() {
        try {

            bot.sendInfo("обращение к training/LightBackground");
            var res = trainingService.getTrainings();
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("LightBackgroundAllTrain")
    public ResponseEntity<Object> getLightBackgroundAllTrain() {
        try {

            bot.sendInfo("обращение к training/LightBackgroundAllTrain");
            var res = trainingService.getAllTrainings();
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }


    @GetMapping("daysOfTrain")
    public ResponseEntity<Object> getTrainingDays(@RequestParam(required = false) Long id) {
        try {

            bot.sendInfo("обращение к training/daysOfTrain");
            var res = trainingService.getTrainingDays(id);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("dayOfTrain")
    public ResponseEntity<Object> getTrainingDay(@RequestParam(required = false) Long id,
                                                 @RequestParam(required = false) Integer numOfDay) {
        try {
            bot.sendInfo("обращение к training/dayOfTrain");
            var res = trainingService.getTrainingDay(id, numOfDay);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }



    @GetMapping("userTrainingProgress")
    public ResponseEntity<Object> getLightBackground(@RequestParam(required = false) Long id) {
        try {
            bot.sendInfo("обращение к training/userTrainingProgress");

            var res = userService.getProgressOfUser(id);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @PostMapping("addTrain")
    public ResponseEntity<Object> addNewTrain(@RequestBody Training train) {
        try {

            bot.sendInfo("обращение к training/addTrain");
            var res = trainingService.addTrain(train);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }
}
