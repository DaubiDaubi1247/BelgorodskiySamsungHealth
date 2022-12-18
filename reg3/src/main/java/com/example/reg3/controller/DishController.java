package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.requastion.DishQuarry;
import com.example.reg3.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("dish")
public class DishController {

    @Autowired
    TelegramBot bot;
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @PostMapping("add")
    public ResponseEntity<Object> addDish(@RequestBody DishQuarry dish) {
        try {
            bot.sendInfo("обращение к dish/add");
            ResponseEntity<Object> res = dishService.addDish(dish);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("getMealTimes")
    public ResponseEntity<Object> getMealTimes() {
        try {
            bot.sendInfo("обращение к dish/add");
            ResponseEntity<Object> res = dishService.getMealTimes();
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("getTypes")
    public ResponseEntity<Object> getTypesOfMeal() {
        try {
            bot.sendInfo("обращение к dish/add");
            ResponseEntity<Object> res = dishService.getTypesOfMeal();
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }




}
