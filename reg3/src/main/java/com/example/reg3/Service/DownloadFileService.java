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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
import java.util.Map;

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




    public void dowloadPDF() throws IOException, DocumentException {

        File jsonFile = new File("src/main/resources/templates/user.json").getAbsoluteFile();

        ObjectMapper mapper = new ObjectMapper();
        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // read map from file
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Map<String, Object> map = mapper.readValue(jsonFile, mapType);

        // generate pretty JSON from map
        String json = mapper.writeValueAsString(map);
        // split by system new lines
        String[] strings = json.split(System.lineSeparator());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        for (String string : strings) {
            contentStream.showText(string);
            // add line manually
            contentStream.newLine();
        }
        contentStream.endText();
        contentStream.close();

        document.save("src/main/resources/templates/myJSON.pdf");
        document.close();
    }
}
