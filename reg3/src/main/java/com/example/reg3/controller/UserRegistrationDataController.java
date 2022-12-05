package com.example.reg3.controller;

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
    private final UserRegistrationDataService userRegistrationDataService;

    @Autowired
    public UserRegistrationDataController(UserRegistrationDataService userService) {
        this.userRegistrationDataService = userService;
    }

    @GetMapping
    public List<UserRegistrationData> getStudents() {
        return userRegistrationDataService.getUsers();
    }

    @PostMapping("registration")
    public UserRegistrationDataRequest registrationUser(@RequestBody UserRegistrationData user)
            throws IllegalAccessException {
        return userRegistrationDataService.addNewUser(user);
    }

    @PostMapping("authentication")
    public UserRegistrationDataRequest authenticationUser(@RequestBody UserRegistrationData user)
            throws IllegalAccessException {

        return userRegistrationDataService.checkUser(user);
    }

}
