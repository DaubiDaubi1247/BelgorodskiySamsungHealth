package com.example.reg3.diet;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dish.Dish;
import com.example.reg3.dish.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietService {
    @Autowired
    TelegramBot bot;
    private final DietRepository dietRepository;

    private final DishRepository dishRepository;

    @Autowired
    public DietService(DietRepository dietRepository, DishRepository dishRepository) {
        this.dietRepository = dietRepository;
        this.dishRepository = dishRepository;
    }

    public ResponseEntity<Object> addDiet(DietQuary dietQuary) {
        var dietInBd = dietRepository.findByLabel(dietQuary.getLabel());

        if (dietInBd.isPresent()) {
            String info = "диета с названием " + dietQuary.getLabel() + " уже есть в БД";
            bot.sendWarning(info);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
        }
        try {
            Diet newDiet = getNewDiet(dietQuary);
            dietRepository.save(newDiet);
            String info = "диета " + newDiet.getLabel() + "успешно добавленно";
            bot.sendInfo(info);

            return ResponseEntity.ok().body(info);
        } catch (Exception e) {
            bot.sendInfo(e.getMessage());
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    private Diet getNewDiet(DietQuary dietQuary) throws Exception {
        Diet newDiet = new Diet();
        newDiet.setIfoWthoutDishes(dietQuary);

        List<Dish> dishes = new ArrayList<>();
        for (var dish : dietQuary.getDishes()) {
            var optionalDish = dishRepository.findByLabel(dish);
            if (optionalDish.isPresent()) {
                dishes.add(optionalDish.get());
            } else {
                String info = "блюдо с название " + dish + " отсутствет в бд";
                bot.sendError(info);
                throw new Exception(info);
            }
        }
        newDiet.setDishes(dishes);
        return newDiet;
    }
}
