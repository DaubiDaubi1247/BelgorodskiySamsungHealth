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
        this.userRegistrationDataService = userService;
    }

    @GetMapping
    public List<UserRegistrationData> getStudents() {
        bot.sendInfo("обращение к hole");
        return userRegistrationDataService.getUsers();
    }

    @PostMapping("registration")
    public UserRegistrationDataRequest registrationUser(@RequestBody UserRegistrationData user) {
        bot.sendInfo("обращение к hole/registration");
        return userRegistrationDataService.addNewUser(user);
    }

    @PostMapping("authentication")
    public UserRegistrationDataRequest authenticationUser(@RequestBody UserRegistrationData user) {
        bot.sendInfo("обращение к hole/authentication");
        return userRegistrationDataService.checkUser(user);
    }

}
