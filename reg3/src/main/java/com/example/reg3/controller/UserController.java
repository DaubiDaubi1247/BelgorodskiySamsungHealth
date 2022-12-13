package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.UserService;
import com.example.reg3.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
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
            (@RequestParam(required =false) Long userId) {
        bot.sendInfo("обращение к user/updayteDayOfTrain");
        return userService.addProgressOfTrain(userId);
    }

    @GetMapping("setTrainToUser")
    public ResponseEntity<Object> addTrainToUser(@RequestParam(required =false) Long userId,
                                                 @RequestParam(required =false) Long trainId) {
        bot.sendInfo("обращение к user/setTrainToUser");
        return userService.setTrainToUser(userId, trainId);
    }

    @PostMapping("setUserData")
    public ResponseEntity<Object> setUserData(@RequestBody User user) {
        bot.sendInfo("обращение к user/setUserData");
        return userService.setUserData(user);
    }

    @GetMapping("getUserData")
    public ResponseEntity<Object> getUserData(@RequestParam(required =false) Long userId) {
        bot.sendInfo("обращение к user/getUserData");
        return userService.getUserData(userId);
    }
}
