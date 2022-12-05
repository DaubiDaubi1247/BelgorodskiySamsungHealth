package com.example.reg3.requastion;

import com.example.reg3.dao.Training;
import com.example.reg3.dao.UserRegistrationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TrainingRequest {
    private Integer status;
    private String message;
    List<Training> trainings;

    public TrainingRequest() {
        status = 404;
        message = "тренеровоки отстутсвуют";
        trainings = null;
    }

    public TrainingRequest(List<Training> trainings) {
        this.trainings = trainings;
        status = 200;
        message = "тренеровки получены успешно";
    }
}
