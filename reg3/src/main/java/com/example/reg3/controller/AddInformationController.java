package com.example.reg3.controller;


import com.example.reg3.Service.TrainingService;
import com.example.reg3.Service.UserRegistrationDataService;
import com.example.reg3.dao.*;
import com.example.reg3.repository.DayOfTrainingRepository;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("add")
public class AddInformationController {

    private UserRegistrationDataRepository userRegistrationData;


    private DayOfTrainingRepository dayOfTrainingRepository;

    private UserRegistrationDataService userRegistrationDataService;


    private TrainingRepository trainingRepository;


    private TrainingService trainingService;

    @Autowired
    public AddInformationController(UserRegistrationDataRepository userRegistrationData,
                                    DayOfTrainingRepository dayOfTrainingRepositor,
                                    TrainingRepository trainingRepository,
                                    UserRegistrationDataService dayOfTrainingService,
                                    TrainingService trainingService) {
        this.trainingRepository = trainingRepository;
        this.dayOfTrainingRepository = dayOfTrainingRepositor;
        this.userRegistrationData = userRegistrationData;
        this.userRegistrationDataService = dayOfTrainingService;
        this.trainingService = trainingService;
    }

    List<Exercise> exerciseList = new ArrayList<>();

    {
        exerciseList.add(new Exercise("Отжиммания", "лечь на пол и толкать землю"));
        exerciseList.add  (new Exercise("Подтягивания", "Подтягиватся"));
            exerciseList.add  (new Exercise("Скручивания", "Скручиватся"));
            exerciseList.add  (new Exercise("Выпрыгивания", "прыгать"));
            exerciseList.add  (new Exercise("Наклоны", "Наклонятся"));
            exerciseList.add  (new Exercise("выкруты", null));
            exerciseList.add  (new Exercise("Брусья", "отжиматся на бруьях"));
            exerciseList.add  (new Exercise("выпды", "менять ноги"));
            exerciseList.add  (new Exercise("шпагат продольный", "сесть на шпагат"));
            exerciseList.add (new Exercise("шпагат поперечный", "сесть на шпагат"));
            exerciseList.add (new Exercise("повороты туловища", "повороты"));
    }


    List<Set> setListPushUps = List.of(
            new Set(10, exerciseList.get(0), 15),
            new Set(20, exerciseList.get(0), 25),
            new Set(30, exerciseList.get(0), 35),
            new Set(40, exerciseList.get(0), 45),
            new Set(50, exerciseList.get(0), 55),
            new Set(60, exerciseList.get(0), 65)
    );


    List<Set> setListJump = List.of(
            new Set(5, exerciseList.get(3), 10),
            new Set(10, exerciseList.get(3), 10),
            new Set(20, exerciseList.get(3), 10)

    );

    List<Set> setListone = List.of(
            new Set(5, exerciseList.get(4), 15),
            new Set(8, exerciseList.get(4), 34),
            new Set(11, exerciseList.get(4), 88)
    );

    List<Set> setListTwo = List.of(
            new Set(5, exerciseList.get(5), 3),
            new Set(12, exerciseList.get(5), 5),
            new Set(13, exerciseList.get(5), 8),
            new Set(14, exerciseList.get(5), 11)
    );


    //todo добавить упражнения
    static List<Training> trainingList = List.of(
            new Training("Гибкость1", 2, "Станешь гибким", "not available"),
            new Training("Сила", 3, "Станешь не очень сильным", "available"),
            new Training("Выносливость", 0, "Станешь немного выносливым", "not available"),
            new Training("Разминание очка", 0, "будем сдавать сис. мод...", "available")
    );


    static List<UserProgressInTraining> userProgressInTrainings = List.of(
            new UserProgressInTraining(1, trainingList.get(0)),
            new UserProgressInTraining(3, trainingList.get(1))
    );


    static List<User> userList = List.of(
            new User("Саня", 68.23f,
                    null, userProgressInTrainings.get(1), 0),
            new User("Александр", 76.24f,
                    178, userProgressInTrainings.get(0), 1),
            new User("Влад", 76.24f,
                    178, null, 5)
    );

    List<DayOfTraining> dayOfTrainingsFloppy1;

    //todo добавить упражнения
    {
        List<Set> setListUp = new ArrayList<>();

        setListUp.add(new Set(5, exerciseList.get(1), 15));
        setListUp.add(new Set(6, exerciseList.get(1), 15));
        setListUp.add(new Set(7, exerciseList.get(1), 15));
        setListUp.add(new Set(8, exerciseList.get(1), 15));

        dayOfTrainingsFloppy1 = new ArrayList<>();

        dayOfTrainingsFloppy1.add(new DayOfTraining(1, setListPushUps));
        dayOfTrainingsFloppy1.add(new DayOfTraining(2, setListJump));
        dayOfTrainingsFloppy1.add(new DayOfTraining(3, setListJump));


    }

    List<UserRegistrationData> userRegistrationDataList = List.of(
            new UserRegistrationData("monokas", "monokas@gmail.com",
                    "monokas", true, userList.get(0)),
            new UserRegistrationData("prosto_chell", "prosto_chell@gmail.com",
                   "prosto_chell", true, userList.get(1)),
            new UserRegistrationData("vlu", "vlu@gmail.com",
                   "vlu", false, userList.get(2))

    );


    @PostMapping("all")
    public void registrationUser() {

        userRegistrationDataService.addNewUser(userRegistrationDataList.get(0));
        userRegistrationDataService.addNewUser(userRegistrationDataList.get(1));
        userRegistrationDataService.addNewUser(userRegistrationDataList.get(2));


//        var training1 = trainingRepository.findById(1L).get();
//        var training2 = trainingRepository.findById(2L).get();
//
//
//        //todo добавить упражнения
//        List<DayOfTraining> dayOfTrainingsFloppy = List.of(
//                new DayOfTraining(1, setListPushUps),
//                new DayOfTraining(2, setListJump),
//                new DayOfTraining(3, setListJump)
//
//        );
//
//        List<DayOfTraining> dayOfTrainingsPower = List.of(
//                new DayOfTraining(1, setListone),
//                new DayOfTraining(2, setListTwo)
//        );
//
//        trainingRepository.saveAll(List.of(trainingList.get(2), trainingList.get(3)));
//        dayOfTrainingRepository.saveAll(dayOfTrainingsPower);
//        dayOfTrainingRepository.saveAll(dayOfTrainingsFloppy);

    }


    @PostMapping("train")
    public void addTrain() {
        var trin = trainingList.get(0);
        trin.setDaysOfTrainings(dayOfTrainingsFloppy1);

        var ans = trainingService.addTrain(trainingList.get(0));

        System.out.println(ans);
    }


}
