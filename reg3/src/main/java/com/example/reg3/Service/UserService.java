package com.example.reg3.Service;


import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.dao.UserProgressInTraining;
import com.example.reg3.repository.TrainingRepository;
import com.example.reg3.repository.UserProgressInTrainingRepository;
import com.example.reg3.repository.UserRepository;
import com.example.reg3.requastion.ProgressOfUserWithPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    TelegramBot bot;

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    private final UserProgressInTrainingRepository userProgressInTrainingRepository;


    @Autowired
    public UserService(TrainingRepository trainingRepository,
                       UserRepository userRepository, UserProgressInTrainingRepository userProgressInTrainingRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.userProgressInTrainingRepository = userProgressInTrainingRepository;
    }


    public ResponseEntity<Object> getProgressOfUser(Long userId) {
        try {
            var userProgress = trainingRepository.findProgressOfUser(userId);

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
        try {
        User user = userRepository.getReferenceById(userId);


        var check = user.getUserProgresInTraining();
        if (check != null) {
            UserProgressInTraining userProgress = user.getUserProgresInTraining();

            if (userProgress.isComplite()) {
                completionTrain(user);
                return ResponseEntity.status(HttpStatus.OK).body("Пользователь завершил программу тренеровок");
            } else {

                    upProgress(userProgress);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body("Пользователь перешел к следующему дню тренеровок программу тренеровок");


            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь не подписан на программу тренеровки");
        }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с данным id не найден");
        }
    }

    private void completionTrain(User user) {
        user.setUserProgresInTraining(null);
        user.incCountOfTrainigs();
        userRepository.save(user);
    }

    private void upProgress(UserProgressInTraining userProgress) throws Exception {
        userProgress = userProgressInTrainingRepository.getReferenceById(userProgress.getId());
        userProgress.incCountOfDays();
        userProgressInTrainingRepository.save(userProgress);
    }


    public ResponseEntity<Object> setTrainToUser(Long userId, Long trainId) {
        try {
            var user = userRepository.getReferenceById(userId);
            var train = trainingRepository.getReferenceById(trainId);

            var progress = new UserProgressInTraining();
            progress.setDayOfTraining(1);
            progress.setTrainingId(train);

            progress = userProgressInTrainingRepository.save(progress);

            user.setUserProgresInTraining(progress);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("пользователь подписан на новую тренеровку");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь или тренеровки с таким id не найдено");
        }
    }

    public ResponseEntity<Object> setUserData(User updateUserdata) {
        try {
            var user = userRepository.getReferenceById(updateUserdata.getId());

            user.setName(updateUserdata.getName());
            user.setWeight(updateUserdata.getWeight());
            user.setHeight(updateUserdata.getHeight());

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("данные пользователя успешно обновлены");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь с таким id не найдено");
        }
    }

    public ResponseEntity<Object> getUserData(Long userId) {

        var userInfo = userRepository.findUserById(userId);

        if (userInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("пользователь с таким id не найдено");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userInfo.get());
    }
}
