package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.TrainingService;
import com.example.reg3.Service.UserRegistrationDataService;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.*;
import com.example.reg3.requastion.DietQuary;
import com.example.reg3.Service.DietService;
import com.example.reg3.requastion.DishQuarry;
import com.example.reg3.Service.DishService;
import com.example.reg3.dao.MealTime;
import com.example.reg3.repository.MealTimeRepository;
import com.example.reg3.repository.DayOfTrainingRepository;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRegistrationDataRepository;
import com.example.reg3.dao.TypeOfMeal;
import com.example.reg3.repository.TypeOfMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final DishService dishService;
    private final MealTimeRepository mealTimeRepository;

    private final DietService dietService;
    private final TypeOfMealRepository typeOfMealRepository;

    @Autowired
    public AddInformationController(UserRegistrationDataRepository userRegistrationData,
                                    DayOfTrainingRepository dayOfTrainingRepositor,
                                    TrainingRepository trainingRepository,
                                    UserRegistrationDataService dayOfTrainingService,
                                    TrainingService trainingService, UserService userService, DishService dishService, MealTimeRepository mealTimeRepository, DishService dietService, DietService dietService1, TypeOfMealRepository typeOfMealRepository) {
        this.trainingRepository = trainingRepository;
        this.dayOfTrainingRepository = dayOfTrainingRepositor;
        this.userRegistrationData = userRegistrationData;
        this.userRegistrationDataService = dayOfTrainingService;
        this.trainingService = trainingService;
        this.userService = userService;
        this.dishService = dishService;
        this.mealTimeRepository = mealTimeRepository;
        this.dietService = dietService1;

        this.typeOfMealRepository = typeOfMealRepository;
    }

    List<Exercise> exerciseList = new ArrayList<>();

    {
        exerciseList.add(new Exercise("Отжиммания", "лечь на пол и толкать землю"));
        exerciseList.add(new Exercise("Подтягивания", "Подтягиватся"));
        exerciseList.add(new Exercise("Скручивания", "Скручиватся"));
        exerciseList.add(new Exercise("Выпрыгивания", "прыгать"));
        exerciseList.add(new Exercise("Наклоны", "Наклонятся"));
        exerciseList.add(new Exercise("выкруты", null));
        exerciseList.add(new Exercise("Брусья", "отжиматся на бруьях"));
        exerciseList.add(new Exercise("выпды", "менять ноги"));
        exerciseList.add(new Exercise("шпагат продольный", "сесть на шпагат"));
        exerciseList.add(new Exercise("шпагат поперечный", "сесть на шпагат"));
        exerciseList.add(new Exercise("повороты туловища", "повороты"));
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
                    "vlu", false, userList.get(2)),
            new UserRegistrationData("man", "man@gmail.com",
                    "man", false, null),
            new UserRegistrationData("Hahatach", "Hahatach@gmail.com",
                    "Hahatach", false, null)

    );

    List<MealTime> mealTimeList = List.of(
            new MealTime("завтрак"),
            new MealTime("обед"),
            new MealTime("ужин"),
            new MealTime("полдник"),
            new MealTime("перекус"),
            new MealTime("второй завтрак")
    );
    List<TypeOfMeal> typeOfMealList = List.of(
            new TypeOfMeal("первое блюдо"),
            new TypeOfMeal("гарнир"),
            new TypeOfMeal("фрукт"),
            new TypeOfMeal("салат"),
            new TypeOfMeal("десерт"),
            new TypeOfMeal("мучное")
    );



    @GetMapping("all")
    public void registrationUser() {
        bot.sendInfo("Вызов http://localhost:8011/add/all");

        //добавление пользователей
        var res = userRegistrationDataService.addNewUser(userRegistrationDataList.get(0));
        var res2 = userRegistrationDataService.addNewUser(userRegistrationDataList.get(1));
        var res3 = userRegistrationDataService.addNewUser(userRegistrationDataList.get(2));
        var res13 = userRegistrationDataService.addNewUser(userRegistrationDataList.get(3));
        var res14 = userRegistrationDataService.addNewUser(userRegistrationDataList.get(4));

        //добавление тренеровок
        var res4 = trainingService.addTrain(trainingList.get(0));
        var res5 = trainingService.addTrain(trainingList.get(1));
        var res6 = trainingService.addTrain(trainingList.get(2));
        var res7 = trainingService.addTrain(trainingList.get(3));

        //подписка пользователей на тренеровки
        var res9 = userService.setTrainToUser(2L, 2L);
        var res8 = userService.setTrainToUser(1L, 1L);

        //сохранение характеристик блюд
        mealTimeRepository.saveAll(mealTimeList);
        typeOfMealRepository.saveAll(typeOfMealList);


        //блюда
        List<DishQuarry> dishQuarryList = List.of(
                new DishQuarry("куриный суп", "первое блюдо", 123, 23.4f,
                        34.2f, 11.2f, List.of("обед", "второй завтрак")),
                new DishQuarry("яблоко", "фрукт", 123, 20.4f,
                        30.2f, 9.2f, List.of("перекус")),
                new DishQuarry("цезарь", "салат", 121, 20.4f,
                        30.2f, 9.2f, List.of("перекус")),
                new DishQuarry("кекс", "десерт", 123, 20.4f,
                        30.2f, 9.2f, List.of("перекус")),
                new DishQuarry("хлеб", "мучное", 123, 20.4f,
                        30.2f, 9.2f, List.of("завтрак", "обед",
                        "ужин", "полдник", "перекус", "второй завтрак"))
        );
        for (var dish:dishQuarryList) {
            dishService.addDish(dish);
        }

        //диеты
        List<String> listDish1 = List.of("цезарь", "куриный суп", "хлеб");
        List<String> listDish2 = List.of("яблоко", "кекс", "хлеб");
        List<String> listDish3 = List.of("яблоко", "цезарь", "кекс", "куриный суп", "хлеб");

        DietQuary dietQuary1 = new DietQuary("Углеводная", "диета на углеводах",null, listDish1);
        DietQuary dietQuary2 = new DietQuary("Белковая", "диета на бЕлках",null, listDish2);
        DietQuary dietQuary3 = new DietQuary("Монодиета", "хз что это",null, listDish3);

        ResponseEntity<Object> res10 = dietService.addDiet(dietQuary1);
        ResponseEntity<Object> res11 = dietService.addDiet(dietQuary2);
        ResponseEntity<Object> res12 = dietService.addDiet(dietQuary3);

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
        msg.append(res10).append("\n");
        msg.append(res11).append("\n");
        msg.append(res12).append("\n");

        bot.sendInfo(msg.toString());
        bot.executeSendLog();

    }
}
