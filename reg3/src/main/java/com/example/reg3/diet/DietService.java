package com.example.reg3.diet;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.repository.DayOfTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService {
    @Autowired
    TelegramBot bot;
    private final DietRepository dietRepository;

    @Autowired
    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

}
