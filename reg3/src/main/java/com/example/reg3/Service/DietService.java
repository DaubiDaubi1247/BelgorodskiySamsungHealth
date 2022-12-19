package com.example.reg3.Service;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.Diet;
import com.example.reg3.dao.Dish;
import com.example.reg3.repository.DietRepository;
import com.example.reg3.requastion.DietQuary;
import com.example.reg3.requastion.DishQuarry;
import com.example.reg3.repository.DishRepository;
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
            return ResponseEntity.status(HttpStatus.OK).body(info);
        }
        try {
            Diet newDiet = getNewDiet(dietQuary);
            dietRepository.save(newDiet);
            String info = "диета " + newDiet.getLabel() + " успешно добавленно";
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


    public ResponseEntity<Object> changeStatusDiet(Long id) {
        try {
            var  diet = dietRepository.getReferenceById(id);
            if (diet.getStatus().equals("not available")){
                diet.setStatus("available");
                dietRepository.save(diet);
                String info = "диета "+diet.getLabel()+ " активирована";
                bot.sendInfo(info);

                return ResponseEntity.status(HttpStatus.OK).body(info);
            } else {
                diet.setStatus("not available");
                dietRepository.save(diet);
                String info = "диета "+diet.getLabel()+ " диактивирована";
                bot.sendInfo(info);

                return ResponseEntity.status(HttpStatus.OK).body(info);
            }
        }catch (Exception e) {
            String info = "диета c id "+id +" не найдена";
            bot.sendInfo(info);

            return ResponseEntity.status(HttpStatus.OK).body(info);
        }
    }

    public ResponseEntity<Object> getAllDietsByStatus(String status) {
        List<Diet> diets;
        if (status == null){
             diets = dietRepository.findAll();
        }else {
             diets = dietRepository.findAllByStatus(status);
        }

        if (diets.size() == 0) {
            String info = "Отсутствуют  диеты co статусом " + status;
            bot.sendWarning(info);

            return ResponseEntity.status(HttpStatus.OK).body(info);
        }else {
            for (var diet: diets) diet.setDishes(null);
            bot.sendInfo("возвращены диеты со статусом " + status);

            return ResponseEntity.status(HttpStatus.OK).body(diets);
        }
    }

    public ResponseEntity<Object> getDishesWithFilters(Long idDiet,
                                                       String typeOfMeal,
                                                       String mailTime) {
        List<Dish> dishList = dietRepository.getDishes(idDiet, typeOfMeal, mailTime);

        if (dishList.size() == 0) {
            String inf = "Отсуттвуют блда с параметрами: " + typeOfMeal + ", " + mailTime + " в диете с id " + idDiet;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(inf);
        } else {
           List< DishQuarry> dishQuarryList = new ArrayList<>();
           for (int i = 0; i < dishList.size(); i++) {
               DishQuarry dishQuarry = new DishQuarry();
               dishQuarry.setParamsFromEntity(dishList.get(i));
               dishQuarryList.add(dishQuarry);
           }

            return ResponseEntity.status(HttpStatus.OK).body(dishQuarryList);
        }
    }
}
