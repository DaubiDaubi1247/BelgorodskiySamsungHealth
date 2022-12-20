package com.example.reg3.Service;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.repository.DishRepository;
import com.example.reg3.repository.MealTimeRepository;
import com.example.reg3.repository.TypeOfMealRepository;
import com.example.reg3.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;
import java.util.List;


@Service
@PropertySource("application.properties")
public class DownloadFileService {

    @Value("${pass.json}")

    private String passToJsonFolder;

    @Value("${pass.pdf}")
    private String passToPDFFolder;

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
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");
        List<User> users = userRepository.findAll();
        saveListObjectsToJSON(Collections.singletonList(users), "/user.pdf");
        bot.sendInfo("Информация из таблицы преобразовалась в JSON");
    }

    private  void saveListObjectsToJSON(List<Object> users, String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File placeOfSave = new File(passToJsonFolder + s);
        mapper.writeValue(placeOfSave, users);
    }

/////
    public void dowloadPDF() throws FileNotFoundException, DocumentException {
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");
        List<User> users = userRepository.findAll();
        bot.sendInfo("Информация из таблицы преобразовалась в JSON");
        saveListObjectsToPDF(Collections.singletonList(users), "/user.pdf");
    }

    private  void saveListObjectsToPDF(List<Object> list, String pass) throws DocumentException, FileNotFoundException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        Document document = new Document();
        pass = passToPDFFolder + pass;
        PdfWriter.getInstance(document, new FileOutputStream(pass));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        var lines = json.split("\n");
        for (var line:lines) {
            Paragraph p = new Paragraph(line);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p);
        }

        Paragraph p = new Paragraph("line");
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(p);
        document.close();
    }
}
