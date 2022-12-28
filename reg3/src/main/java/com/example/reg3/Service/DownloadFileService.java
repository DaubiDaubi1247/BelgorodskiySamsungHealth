package com.example.reg3.Service;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.repository.*;
import com.example.reg3.requastion.StatisticOfTrain;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static com.itextpdf.text.html.HtmlTags.FONT;


@Service
@PropertySource("application.properties")
public class DownloadFileService {

    @Value("${pass.json}")

    private String passToJsonFolder;

    @Value("${pass.pdf}")
    private String passToPDFFolder;

    @Autowired
    TelegramBot bot;

    private final TrainingRepository trainingRepository;


    @Autowired
    public DownloadFileService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;


    }

    public void downloadTopOfTraningsStatistic(Integer percentOfProgress) throws IOException {
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");

        List<StatisticOfTrain> statistic = trainingRepository.findTopOfTranings(Double.valueOf(percentOfProgress));
        saveListObjectsToJSON(statistic);
        bot.sendInfo("Информация из таблицы преобразовалась в JSON");
    }

    private void saveListObjectsToJSON(List<StatisticOfTrain> statistic) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File placeOfSave = new File(passToJsonFolder + "/user.json");
        mapper.writeValue(placeOfSave, statistic);
    }

    /////
    public void dowloadPDF(Integer percentOfProgress) throws FileNotFoundException, DocumentException {
        bot.sendInfo("Запрсо всех записей из таблицы пользователей");
        List<StatisticOfTrain> statistic = trainingRepository.findTopOfTranings(Double.valueOf(percentOfProgress));
        bot.sendInfo("Информация из таблицы преобразовалась в JSON");
        saveListObjectsToPDF(statistic, "/user.pdf");
    }

    private void saveListObjectsToPDF(List<StatisticOfTrain> list, String pass) throws DocumentException, FileNotFoundException {

        Document document = new Document();
        pass = passToPDFFolder + pass;
        PdfWriter.getInstance(document, new FileOutputStream(pass));
        document.open();

        for (var stat : list) {
            addStat(document, stat);
        }
        document.add(new Paragraph());
        document.close();
    }

    private static void addStat(Document document, StatisticOfTrain stat) throws DocumentException {
        Font font = FontFactory.getFont(FONT, "Cp1250", true);;
        Paragraph p = new Paragraph("Название тренеровки: " + stat.getLabel(), font);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(p);
        Paragraph p2 = new Paragraph("Кол-во пользователей: " + stat.getCountOfUsers(), font);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(p2);
        Paragraph p3 = new Paragraph("========================", font);
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(p3);
    }
}
