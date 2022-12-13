package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.DayOfTrainingService;
import com.example.reg3.Service.TrainingService;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.Training;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> deactivateTrain(@RequestParam(required =false) Long trainId) {
        bot.sendInfo("обращение к training/deactivate");
        return trainingService.deactivateTrain(trainId);
    }

    @GetMapping("active")
    public ResponseEntity<Object> activeTrain(@RequestParam(required =false) Long trainId) {
        bot.sendInfo("обращение к training/active");
        return trainingService.activeTrain(trainId);
    }

    @GetMapping("LightBackground")
    public ResponseEntity<Object> getLightBackground() {
        bot.sendInfo("обращение к training/LightBackground");

        return trainingService.getTrainings();
    }

    @GetMapping("LightBackgroundAllTrain")
    public ResponseEntity<Object> getLightBackgroundAllTrain() {
        bot.sendInfo("обращение к training/LightBackgroundAllTrain");

        return trainingService.getAllTrainings();
    }




    @GetMapping("daysOfTrain")
    public ResponseEntity<Object> getTrainingDays(@RequestParam(required =false) Long id) {
        bot.sendInfo("обращение к training/daysOfTrain");

        return trainingService.getTrainingDays(id);
    }

    @GetMapping("dayOfTrain")
    public ResponseEntity<Object> getTrainingDay(@RequestParam(required =false) Long id,
                                                 @RequestParam(required =false) Integer numOfDay) {
        bot.sendInfo("обращение к training/dayOfTrain");
        return trainingService.getTrainingDay(id, numOfDay);
    }

    @GetMapping("userTrainingProgress")
    public ResponseEntity<Object> getLightBackground(@RequestParam(required =false) Long id) {
        bot.sendInfo("обращение к training/userTrainingProgress");

        return userService.getProgressOfUser(id);
    }

    @PostMapping("addTrain")
    public ResponseEntity<Object> addNewTrain(@RequestBody Training train) {
        bot.sendInfo("обращение к training/addTrain");
        return trainingService.addTrain(train);
    }


}
