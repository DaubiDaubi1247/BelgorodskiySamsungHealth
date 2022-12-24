package com.example.reg3.controller;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.DownloadFileService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("download")
@PropertySource("application.properties")
public class DownloadFileController {

    @Value("${pass.json}")
    private  String passToJsonFolder;

    @Value("${pass.pdf}")
    private  String passToPDFFolder;

    @Autowired
    TelegramBot bot;
    DownloadFileService downloadFileService;

    @Autowired
    public DownloadFileController(DownloadFileService dishService) {
        this.downloadFileService = dishService;
    }


    @GetMapping("user/json")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getJsonStatistic(Integer percentOfProgress) throws IOException {
        downloadFileService.downloadTopOfTraningsStatistic(percentOfProgress);

        MediaType contentType =  MediaType.APPLICATION_JSON;
        InputStreamResource in = new InputStreamResource(new FileInputStream(passToJsonFolder + "/user.json"));
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(in);
    }

    @GetMapping("user/pdf")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getPdfStatistc(Integer percentOfProgress)
            throws IOException, DocumentException {

        MediaType contentType = MediaType.APPLICATION_PDF;
        downloadFileService.dowloadPDF(percentOfProgress);

        InputStreamResource in = new InputStreamResource(new FileInputStream(passToPDFFolder + "/user.pdf"));

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(in);
    }
}
