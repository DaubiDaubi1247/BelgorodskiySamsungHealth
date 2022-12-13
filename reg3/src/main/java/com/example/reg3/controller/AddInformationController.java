package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.TrainingService;
import com.example.reg3.Service.UserRegistrationDataService;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.*;
import com.example.reg3.repository.DayOfTrainingRepository;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("add")
public class AddInformationController {

    @Autowired
    TelegramBot bot;

    private UserRegistrationDataRepository userRegistrationData;
    private final DayOfTrainingRepository dayOfTrainingRepository;
    private final UserRegistrationDataService userRegistrationDataService;
    private final TrainingRepository trainingRepository;
    private final TrainingService trainingService;
    private final UserService userService;
    @Autowired
    public AddInformationController(UserRegistrationDataRepository userRegistrationData,
                                    DayOfTrainingRepository dayOfTrainingRepositor,
                                    TrainingRepository trainingRepository,
                                    UserRegistrationDataService dayOfTrainingService,
                                    TrainingService trainingService, UserService userService) {
        this.trainingRepository = trainingRepository;
        this.dayOfTrainingRepository = dayOfTrainingRepositor;
        this.userRegistrationData = userRegistrationData;
        this.userRegistrationDataService = dayOfTrainingService;
        this.trainingService = trainingService;
        this.userService = userService;
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


     List<DayOfTraining> daysOfTrainingsFloppy1 = List.of(
            new DayOfTraining(1, setListPushUps),
            new DayOfTraining(2, setListJump),
            new DayOfTraining(3, setListTwo)
    );

    List<DayOfTraining> daysOfTrainingsPower = List.of(
            new DayOfTraining(1, setListone),
            new DayOfTraining(2, setListJump),
            new DayOfTraining(3, setListJump)
    );

    //todo добавить упражнения
//    {
//        List<Set> setListUp = new ArrayList<>();
//
//        setListUp.add(new Set(5, exerciseList.get(1), 15));
//        setListUp.add(new Set(6, exerciseList.get(1), 15));
//        setListUp.add(new Set(7, exerciseList.get(1), 15));
//        setListUp.add(new Set(8, exerciseList.get(1), 15));
//
//    }



     List<Training> trainingList = List.of(
            new Training("Гибкость1", 2, "Станешь гибким", "available", daysOfTrainingsFloppy1),
            new Training("Сила", 3, "Станешь не очень сильным", "available", daysOfTrainingsPower),
            new Training("Выносливость", 0, "Станешь немного выносливым", "available", new ArrayList<>()),
            new Training("Разминание очка", 0, "будем сдавать сис. мод...", "not available", new ArrayList<>())
    );


//    static List<UserProgressInTraining> userProgressInTrainings = List.of(
//            new UserProgressInTraining(1, trainingList.get(0)),
//            new UserProgressInTraining(3, trainingList.get(1))
//    );


    static List<User> userList = List.of(
            new User("Саня", 68.23f, null, null, 0),
            new User("Александр", 76.24f, 178, null, 1),
            new User("Влад", 76.24f, 178, null, 5)
    );


//Пользовательские данные
    List<UserRegistrationData> userRegistrationDataList = List.of(
            new UserRegistrationData("monokas", "monokas@gmail.com",
                    "monokas", true, userList.get(0)),
            new UserRegistrationData("prosto_chell", "prosto_chell@gmail.com",
                   "prosto_chell", true, userList.get(1)),
            new UserRegistrationData("vlu", "vlu@gmail.com",
                   "vlu", false, userList.get(2))

    );


    @GetMapping("all")
    public void registrationUser() {
        bot.sendLog("Вызов http://localhost:8011/add/all");
        var res = userRegistrationDataService.addNewUser(userRegistrationDataList.get(0));
        var res2 =  userRegistrationDataService.addNewUser(userRegistrationDataList.get(1));
        var res3 = userRegistrationDataService.addNewUser(userRegistrationDataList.get(2));

        var res4 = trainingService.addTrain(trainingList.get(0));
        var res5 = trainingService.addTrain(trainingList.get(1));
        var res6 = trainingService.addTrain(trainingList.get(2));
        var res7 = trainingService.addTrain(trainingList.get(3));


        var res9 = userService.setTrainToUser(2L, 2L);
        var res8 = userService.setTrainToUser(1L, 1L);

        StringBuilder msg = new StringBuilder();
        msg.append("Добавление изначальных данных\n");
        msg.append(res).append("\n");
        msg.append(res2).append("\n");
        msg.append(res3).append("\n");
        msg.append(res4).append("\n");
        msg.append(res5).append("\n");
        msg.append(res6).append("\n");
        msg.append(res7).append("\n");
        msg.append(res8).append("\n");
        msg.append(res9).append("\n");
        bot.sendLog(msg.toString());

    }


    @PostMapping("train")
    public void addTrain() {
        var trin = trainingList.get(0);
        trin.setDaysOfTrainings(daysOfTrainingsFloppy1);

        var ans = trainingService.addTrain(trainingList.get(0));

        System.out.println(ans);
    }


    @GetMapping("getTrainJSON")
    public Training getDayOf() {
        return trainingList.get(1);
    }
}
