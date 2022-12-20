package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("user")
public class UserController {
    @Autowired
    TelegramBot bot;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("updayteDayOfTrain")

    public ResponseEntity<Object> updateDayOfTraining
            (@RequestParam(required = false) Long userId) {
        try {
            bot.sendInfo("обращение к user/updayteDayOfTrain");
            var res = userService.addProgressOfTrain(userId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("setTrainToUser")
    public ResponseEntity<Object> addTrainToUser(@RequestParam(required = false) Long userId,
                                                 @RequestParam(required = false) Long trainId) {
        try {
            bot.sendInfo("обращение к user/setTrainToUser");
            var res = userService.setTrainToUser(userId, trainId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("setDietToUser")
    public ResponseEntity<Object> addDietToUser(@RequestParam(required = false) Long userId,
                                                 @RequestParam(required = false) Long dietId) {
        try {
            bot.sendInfo("обращение к user/setTrainToUser");
            var res = userService.setDietToUser(userId, dietId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }



    @PostMapping("setUserData")
    public ResponseEntity<Object> setUserData(@RequestBody User user) {
        try {
            bot.sendInfo("обращение к user/setUserData");
            var res = userService.setUserData(user);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("getUserData")
    public ResponseEntity<Object> getUserData(@RequestParam(required = false) Long userId) {
        try {
            bot.sendInfo("обращение к user/getUserData");
            var res = userService.getUserData(userId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

    @GetMapping("getDiet")
    public ResponseEntity<Object> getDiet(@RequestParam Long userId) {
        try {
            bot.sendInfo("обращение к user/getUserData");
            var res = userService.getDiet(userId);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.toString());
        }
    }

}
