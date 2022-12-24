package com.example.reg3.Service;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.Dish;
import com.example.reg3.dao.MealTime;
import com.example.reg3.repository.DishRepository;
import com.example.reg3.repository.MealTimeRepository;
import com.example.reg3.dao.TypeOfMeal;
import com.example.reg3.repository.TypeOfMealRepository;
import com.example.reg3.requastion.DishQuarry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    TelegramBot bot;
    private final DishRepository dishRepository;
    private final MealTimeRepository mealTimeRepository;
    private final TypeOfMealRepository typeOfMealRepository;

    @Autowired
    public DishService(DishRepository dishRepository,
                       MealTimeRepository mealTimeRepository, TypeOfMealRepository typeOfMealRepository) {
        this.dishRepository = dishRepository;
        this.mealTimeRepository = mealTimeRepository;
        this.typeOfMealRepository = typeOfMealRepository;
    }

    public ResponseEntity<Object> addDish(DishQuarry dishQuarry) {

        Optional<Dish> dishInBd = dishRepository.findByLabel(dishQuarry.getLabel());
        if (dishInBd.isPresent()) {
            String anser = "Блюдо с названием " + dishQuarry.getLabel() + " уже присутствует в БД";
            bot.sendWarning(anser);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(anser);
        }

        try {
            Dish newDish = createNewDish(dishQuarry);
            dishRepository.save(newDish);
            bot.sendInfo("блюдо " + newDish.getLabel() + "успешно добавленно");

            return ResponseEntity.ok().body("блюдо добавленно");
        } catch (Exception e) {
            bot.sendError(e.getMessage());

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Dish createNewDish(DishQuarry dishQuarry) throws Exception {
        Dish newDish = new Dish();
        newDish.setParamsWithOutMealTime(dishQuarry);

        setTypeToDish(dishQuarry, newDish);
        setMealTimeTiDish(dishQuarry, newDish);

        return newDish;
    }

    private void setMealTimeTiDish(DishQuarry dishQuarry, Dish newDish) throws Exception {
        List<MealTime> listNewMileTimes = new ArrayList<>();
        for (var mealTime : dishQuarry.getMealTimes()) {
            var optionalMealTime
                    = mealTimeRepository.findMealTimeByLabel(mealTime.toLowerCase());
            if (optionalMealTime.isPresent()) {
                listNewMileTimes.add(optionalMealTime.get());
            } else {
                bot.sendError("прием пищи с название " + mealTime + " отсутствет в бд");
                throw new Exception("прием пищи с название " + mealTime + " отсутствет в бд");
            }
        }
        newDish.setMealTimes(listNewMileTimes);
    }

    private void setTypeToDish(DishQuarry dishQuarry, Dish newDish) throws Exception {
        Optional <TypeOfMeal> typeOfMeal = typeOfMealRepository.findByLabel(dishQuarry.getType());
        if (typeOfMeal.isEmpty()){
            String anser = "Тип блюда с названием " + dishQuarry.getType() + " отсутствует в БД";
            bot.sendWarning(anser);
            throw  new Exception(anser);
        }
        newDish.setType(typeOfMeal.get());
    }

    public ResponseEntity<Object> getMealTimes() {
       var mealTimes =  mealTimeRepository.findAll();
       if (mealTimes.size() == 0) {
           bot.sendWarning("приемы пищи отсутствуют в БД");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("приемы пищи отсутствуют в БД");
       }

       List<String> labelOfMealTimes = new ArrayList<>();

       for (int i = 0; i < mealTimes.size(); i++) {
           labelOfMealTimes.add(mealTimes.get(i).getLabel());
       }
        bot.sendInfo("прием пищи взят из БД");
       return ResponseEntity.ok().body(labelOfMealTimes);
    }

    public ResponseEntity<Object> getTypesOfMeal() {
        var mealTimes =   typeOfMealRepository.findAll();
        if (mealTimes.size() == 0) {
            bot.sendWarning("типы блюда отсутствуют в БД");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("типы блюда отсутствуют в БД");

        }

        List<String> labelOfMealTypes = new ArrayList<>();

        for (int i = 0; i < mealTimes.size(); i++) {
            labelOfMealTypes.add(mealTimes.get(i).getLabel());
        }
        bot.sendInfo("тип , блюда взят из БД");
        return ResponseEntity.ok().body(labelOfMealTypes);
    }
}
