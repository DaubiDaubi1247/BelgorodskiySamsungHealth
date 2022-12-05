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
public class TrainingRequest extends Request {

    List<Training> trainings;

    public TrainingRequest() {
        super(404, "тренеровоки отстутсвуют");
        trainings = null;
    }

    public TrainingRequest(List<Training> trainings) {
        super(404, "тренеровоки отстутсвуют");
        this.trainings = trainings;

    }
}
