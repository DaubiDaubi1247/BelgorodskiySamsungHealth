package com.example.reg3.controller;


import com.example.reg3.Service.UserRegistrationDataService;
import com.example.reg3.dao.*;
import com.example.reg3.repository.DayOfTrainingRepository;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("add")
public class AddInformationController {

    private UserRegistrationDataRepository userRegistrationData;


    private DayOfTrainingRepository dayOfTrainingRepository;

    private UserRegistrationDataService userRegistrationDataService;


    private TrainingRepository trainingRepository;

    @Autowired
    public AddInformationController(UserRegistrationDataRepository userRegistrationData,
                                    DayOfTrainingRepository dayOfTrainingRepositor,
                                     TrainingRepository trainingRepository,
                                    UserRegistrationDataService dayOfTrainingService) {
        this.trainingRepository = trainingRepository;
        this.dayOfTrainingRepository = dayOfTrainingRepositor;
        this.userRegistrationData = userRegistrationData;
        this.userRegistrationDataService = dayOfTrainingService;
    }
    List<Exercise> exerciseList = List.of(
            new Exercise("Отжиммания", "лечь на пол и толкать землю"),
            new Exercise("Подтягивания", "Подтягиватся"),
            new Exercise("Скручивания", "Скручиватся"),
            new Exercise("Выпрыгивания", "прыгать"),
            new Exercise("Наклоны","Наклонятся"),
            new Exercise("выкруты",null),
            new Exercise("Брусья","отжиматся на бруьях"),
            new Exercise("выпды","менять ноги"),
            new Exercise("шпагат продольный","сесть на шпагат"),
            new Exercise("шпагат поперечный","сесть на шпагат"),
            new Exercise("повороты туловища","повороты")
    );

    List<Set> setListPushUps = List.of(
            new Set(10,  exerciseList.get(0), 15),
            new Set(20,  exerciseList.get(0), 25),
            new Set(30,  exerciseList.get(0), 35),
            new Set(40,  exerciseList.get(0), 45),
            new Set(50,  exerciseList.get(0), 55),
            new Set(60,  exerciseList.get(0), 65)
    );

    List<Set> setListUp = List.of(
            new Set(5,  exerciseList.get(1), 15),
            new Set(6,  exerciseList.get(1), 15),
            new Set(7,  exerciseList.get(1), 15),
            new Set(8,  exerciseList.get(1), 15)
    );

    List<Set> setListJump = List.of(
            new Set(5,  exerciseList.get(3), 10),
            new Set(10,  exerciseList.get(3), 10),
            new Set(20,  exerciseList.get(3), 10)

    );

    List<Set> setListone = List.of(
            new Set(5,  exerciseList.get(4), 15),
            new Set(8,  exerciseList.get(4), 34),
            new Set(11,  exerciseList.get(4), 88)
    );

    List<Set> setListTwo = List.of(
            new Set(5,  exerciseList.get(5), 3),
            new Set(12,  exerciseList.get(5), 5),
            new Set(13,  exerciseList.get(5), 8),
            new Set(14,  exerciseList.get(5), 11)
    );






    //todo добавить упражнения



    static List<Training> trainingList = List.of(
            new Training("Гибкость", 2, "Станешь гибким"),
            new Training("Сила", 3, "Станешь не очень сильным"),
            new Training("Выносливость", 0, "Станешь немного выносливым"),
            new Training("Разминание очка", 0, "будем сдавать сис. мод...")
    );



    static List<UserProgressInTraining> userProgressInTrainings = List.of(
            new UserProgressInTraining(1, trainingList.get(0)),
            new UserProgressInTraining(3, trainingList.get(1))
    );


    static List<User> userList = List.of(
            new User( "Саня", 68.23f,
                    null, userProgressInTrainings.get(1),0 ),
            new User("Александр", 76.24f,
                    178, userProgressInTrainings.get(0), 1),
            new User("Влад", 76.24f,
                    178, null, 5)
    );



    @PostMapping("all")
    public void registrationUser() {

         List<UserRegistrationData> userRegistrationDataList = List.of(
                new UserRegistrationData("monokas", "monokas@gmail.com",
                        userRegistrationDataService.cipherStrWithLocalKey("monokas") , true, userList.get(0)),
                new UserRegistrationData("prosto_chell", "prosto_chell@gmail.com",
                        userRegistrationDataService.cipherStrWithLocalKey("prosto_chell"), true, userList.get(1)),
                new UserRegistrationData("vlu", "vlu@gmail.com",
                        userRegistrationDataService.cipherStrWithLocalKey("vlu"), true, userList.get(2))

        );


        userRegistrationData.saveAll(userRegistrationDataList);




        var training1 = trainingRepository.findById(1L).get();
        var training2 = trainingRepository.findById(2L).get();


        //todo добавить упражнения
        List<DayOfTraining> dayOfTrainingsFloppy = List.of(
                new DayOfTraining(1, training1, setListPushUps),
                new DayOfTraining(2, training1, setListUp),
                new DayOfTraining(3, training1, setListJump)

        );

        List<DayOfTraining> dayOfTrainingsPower = List.of(
                new DayOfTraining(1, training2, setListone),
                new DayOfTraining(2, training2, setListTwo)
        );

        trainingRepository.saveAll(List.of(trainingList.get(2), trainingList.get(3)));
        dayOfTrainingRepository.saveAll(dayOfTrainingsPower);
        dayOfTrainingRepository.saveAll(dayOfTrainingsFloppy);

    }
}
