package com.example.reg3.Service;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.repository.DishRepository;
import com.example.reg3.repository.MealTimeRepository;
import com.example.reg3.repository.TypeOfMealRepository;
import com.example.reg3.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DownloadFileService {

    @Autowired
    TelegramBot bot;
    private final DishRepository dishRepository;
    private final MealTimeRepository mealTimeRepository;
    private final TypeOfMealRepository typeOfMealRepository;
    private final UserRepository userRepository;


    @Autowired
    public DownloadFileService(DishRepository dishRepository,
                               MealTimeRepository mealTimeRepository,
                               TypeOfMealRepository typeOfMealRepository,
                               UserRepository userRepository) {

        this.dishRepository = dishRepository;
        this.mealTimeRepository = mealTimeRepository;
        this.typeOfMealRepository = typeOfMealRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> downloadUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");
        List<User> users = userRepository.findAll();

        File placeOfSave =new File("src/main/resources/templates/user.json");

        mapper.writeValue(placeOfSave, users);

        bot.sendInfo("Информация из таблицы преобразовалась в JSON");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(placeOfSave));

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Disposition", String.format("attachment; filename=%s", placeOfSave.getName()));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(placeOfSave.length())
                .contentType(MediaType.parseMediaType("application/json"))
                .body(resource);
    }


}
