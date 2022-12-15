package com.example.reg3.diet;

import com.example.reg3.LogBot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("diet")
public class DietController {

    @Autowired
    TelegramBot bot;

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping("add")
    public ResponseEntity<Object> addDiet(@RequestBody DietQuary dietQuary ) {
        try {
            bot.sendInfo("обращение к diet/add");
            ResponseEntity<Object> res = dietService.addDiet(dietQuary);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("changeStatus")
    public ResponseEntity<Object> changeStatusDiet
            (@RequestParam Long id) {
        try {
            bot.sendInfo("обращение к diet/changeStatus");
            ResponseEntity<Object> res = dietService.changeStatusDiet(id);

            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }


    @GetMapping("getAll")
    public ResponseEntity<Object> getAllDiets(@RequestParam(required = false) String status) {
        try {
            bot.sendInfo("обращение к diet/getAll status=" + status);
            ResponseEntity<Object> res = dietService.getAllDietsByStatus(status);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

}
