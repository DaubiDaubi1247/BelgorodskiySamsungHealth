package com.example.reg3.diet;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




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



}
