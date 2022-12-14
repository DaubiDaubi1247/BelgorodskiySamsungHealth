package com.example.reg3.controller;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.UserRegistrationDataService;
import com.example.reg3.dao.UserRegistrationData;
import com.example.reg3.requastion.UserRegistrationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("hole")
public class UserRegistrationDataController {
    @Autowired
    TelegramBot bot;
    private final UserRegistrationDataService userRegistrationDataService;

    @Autowired
    public UserRegistrationDataController(UserRegistrationDataService userService) {
        try {
        this.userRegistrationDataService = userService;
        } catch (Exception e) {
            bot.executeSendLog();
            throw e;
        }
    }

    @GetMapping
    public List<UserRegistrationData> getStudents() {
        try {
            bot.sendInfo("обращение к hole");
            var res = userRegistrationDataService.getUsers();
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            throw e;
        }
    }

    @PostMapping("registration")
    public UserRegistrationDataRequest registrationUser(@RequestBody UserRegistrationData user) {
        try {
            bot.sendInfo("обращение к hole/registration");
            var res = userRegistrationDataService.addNewUser(user);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            throw e;
        }
    }

    @PostMapping("authentication")
    public UserRegistrationDataRequest authenticationUser(@RequestBody UserRegistrationData user) {
        try {
            bot.sendInfo("обращение к hole/authentication");
            var res = userRegistrationDataService.checkUser(user);
            bot.executeSendLog();
            return res;
        } catch (Exception e) {
            bot.executeSendLog();
            throw e;
        }
    }

}
