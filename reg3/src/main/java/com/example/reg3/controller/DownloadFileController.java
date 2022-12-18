package com.example.reg3.controller;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.Service.DishService;
import com.example.reg3.Service.DownloadFileService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("download")
public class DownloadFileController {
    @Autowired
    TelegramBot bot;
    DownloadFileService downloadFileService;

    @Autowired
    public DownloadFileController(DownloadFileService dishService) {
        this.downloadFileService = dishService;
    }


    @GetMapping("/diet")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDynamicType() throws IOException {
        MediaType contentType =  MediaType.APPLICATION_JSON;
downloadFileService.downloadUsers();
        String file = "src/main/resources/templates/user.json";
        InputStreamResource in = new InputStreamResource(new FileInputStream(file));
        assert in != null;
        return ResponseEntity.ok()

                .contentType(contentType)
                .body(in);
    }

    @GetMapping("/diet2")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDyn222()
            throws IOException, DocumentException {

        MediaType contentType = MediaType.APPLICATION_PDF;
        downloadFileService.dowloadPDF();
        String file = "src/main/resources/templates/myJSON.pdf";

        InputStreamResource in = new InputStreamResource
                (new FileInputStream(file));
        assert in != null;
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(in);


    }
}
