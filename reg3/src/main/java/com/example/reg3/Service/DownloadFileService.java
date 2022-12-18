package com.example.reg3.Service;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.repository.DishRepository;
import com.example.reg3.repository.MealTimeRepository;
import com.example.reg3.repository.TypeOfMealRepository;
import com.example.reg3.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.*;
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

    public void downloadUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");
        List<User> users = userRepository.findAll();

        File placeOfSave =new File("src/main/resources/templates/user.json");

        mapper.writeValue(placeOfSave, users);

        bot.sendInfo("Информация из таблицы преобразовалась в JSON");




    }




    public void dowloadPDF() throws FileNotFoundException, DocumentException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/templates/myJSON.pdf"));
        List<User> users = userRepository.findAll();
        String jsonString = mapper.writeValueAsString(users);
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(jsonString, font);

        document.add(chunk);
        document.close();
    }
}
