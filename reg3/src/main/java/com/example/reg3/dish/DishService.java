package com.example.reg3.dish;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.meal.MealTime;
import com.example.reg3.meal.MealTimeRepository;
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

    @Autowired
    public DishService(DishRepository dishRepository,
                       MealTimeRepository mealTimeRepository) {
        this.dishRepository = dishRepository;
        this.mealTimeRepository = mealTimeRepository;
    }

    public ResponseEntity<Object> addDish(DishQuarry dishQuarry) {

        Optional<Dish> dishInBd = dishRepository.findByLabel(dishQuarry.getLabel());

        if (dishInBd.isPresent()) {
            String anser = "Блюдо с названием " + dishQuarry.getLabel() + "уже присутствует в БД";
            bot.sendWarning(anser);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(anser);
        }

        try {
            Dish newDish = createNewDish(dishQuarry);
            dishRepository.save(newDish);
            bot.sendInfo("блюдо " + newDish.getLabel() + "успешно добавленно");
            return ResponseEntity.ok().body("блюдо добавленно");
        } catch (Exception e) {
            bot.sendInfo(e.getMessage());
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    private Dish createNewDish(DishQuarry dishQuarry) throws Exception {
        Dish newDish = new Dish();
        newDish.setParamsWithOutMealTime(dishQuarry);

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

        return newDish;
    }

    public ResponseEntity<Object> getMealTimes() {
       var mealTimes =   mealTimeRepository.findAll();
       if (mealTimes.size() == 0) {
           bot.sendWarning("прием пищи отсутствуют в БД");
       }

       List<String> labelOfMealTimes = new ArrayList<>();

       for (int i = 0; i < mealTimes.size(); i++) {
           labelOfMealTimes.add(mealTimes.get(i).getLabel());
       }
        bot.sendInfo("прием пищи отсутствуют в БД");
       return ResponseEntity.ok().body(labelOfMealTimes);
    }
}
