package com.example.reg3.Service;


import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRegistrationDataRepository;
import com.example.reg3.requastion.TrainingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;


    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public TrainingRequest getTrainings() {

        var trainingListings = trainingRepository.findAll();

        if (trainingListings.size() == 0) {
            return new TrainingRequest();
        }
        else {
            return new TrainingRequest(trainingListings);
        }

    }

}
