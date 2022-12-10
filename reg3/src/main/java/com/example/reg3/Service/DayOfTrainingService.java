package com.example.reg3.Service;

import com.example.reg3.repository.DayOfTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DayOfTrainingService {

    private final DayOfTrainingRepository dayOfTrainingRepository;

    @Autowired
    public DayOfTrainingService(DayOfTrainingRepository dayOfTrainingRepository) {
        this.dayOfTrainingRepository = dayOfTrainingRepository;
    }






}
