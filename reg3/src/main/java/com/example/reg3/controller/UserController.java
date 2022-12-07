package com.example.reg3.controller;


import com.example.reg3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("updayteDayOfTrain")
    public ResponseEntity<Object> updateDayOfTraining
            (@RequestParam(required =false) Long userId) {
        return userService.addProgressOfTrain(userId);

    }



}
