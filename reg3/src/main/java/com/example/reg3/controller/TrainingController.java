package com.example.reg3.controller;


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

    @GetMapping("LightBackground")
    public ResponseEntity<Object> getLightBackground() {
        return trainingService.getTrainings();
    }

    //todo переделать
//    @GetMapping("daysOfTrain")
//    public ResponseEntity<Object> getTrainingDays(@RequestParam(required =false) Long id) {
//        return dayOfTrainingService.getTrainingDays(id);
//    }
//
//    @GetMapping("dayOfTrain")
//    public ResponseEntity<Object> getTrainingDay(@RequestParam(required =false) Long id,
//                                                 @RequestParam(required =false) Integer numOfDay) {
//        return dayOfTrainingService.getTrainingDay(id, numOfDay);
//    }

    @GetMapping("userTrainingProgress")
    public ResponseEntity<Object> getLightBackground(@RequestParam(required =false) Long id) {
        return userService.getProgressOfUser(id);
    }

    @PostMapping("addTrain")
    public ResponseEntity<Object> addNewTrain(@RequestBody Training train) {
        return trainingService.addTrain(train);
    }

}
