package com.example.reg3.Service;


import com.example.reg3.repository.UserRepository;
import com.example.reg3.requastion.ProgressOfUserWithPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> getProgressOfUser(Long userId) {
        var userProgress =
                userRepository.findProgressOfUser(userId);

        if(userProgress.size() == 1){
            var progress =  new ProgressOfUserWithPresent(userProgress.get(0));
            return ResponseEntity.status(HttpStatus.OK).body(progress);
        }
        else if (userProgress.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("прогресс не найден");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("непредвиденная ошибка");
        }

    }
}
