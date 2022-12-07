package com.example.reg3.Service;


import com.example.reg3.dao.User;
import com.example.reg3.dao.UserProgressInTraining;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserRepository;
import com.example.reg3.requastion.ProgressOfUserWithPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;


    @Autowired
    public UserService(TrainingRepository trainingRepository,
                       UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> getProgressOfUser(Long userId) {
        try {
            var userProgress =
                    trainingRepository.findProgressOfUser(userId);

            if (userProgress.size() == 1) {
                var progress = new ProgressOfUserWithPresent(userProgress.get(0));
                return ResponseEntity.status(HttpStatus.OK).body(progress);
            } else if (userProgress.size() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("прогресс не найден");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("непредвиденная ошибка");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("не найден прогресс ползьзователя");
        }
    }

    public ResponseEntity<Object> addProgressOfTrain(Long userId) {
        User user = userRepository.getReferenceById(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь с таким id не найдет");
        }

        if (user.getUserProgresInTraining() != null) {
            UserProgressInTraining userProgress = user.getUserProgresInTraining();

            if (userProgress.isComplite()) {
                user.setUserProgresInTraining(null);

                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Пользователь завершил программу тренеровок");
            }else {
                user.incCountOfTrainigs();
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Пользователь перешел к следующему дню тренеровок программу тренеровок");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь не подписан на программу тренеровки");
        }
    }


}
