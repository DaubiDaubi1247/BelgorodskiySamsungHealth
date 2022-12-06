package com.example.reg3.Service;

import com.example.reg3.dao.UserRegistrationData;
import com.example.reg3.repository.UserRegistrationDataRepository;
import com.example.reg3.requastion.UserRegistrationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationDataService {

    private final UserRegistrationDataRepository userRepository;


    @Autowired
    public UserRegistrationDataService(UserRegistrationDataRepository
                                                   userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserRegistrationData> getUsers() {
        return userRepository.findAll();
    }

    public UserRegistrationDataRequest addNewUser(UserRegistrationData usersOfApp)
            throws IllegalAccessException {
        Optional<UserRegistrationData> userOptional =
                userRepository.findUserByEmail(usersOfApp.getEmail());

        if (userOptional.isPresent()) {
            return new UserRegistrationDataRequest
                    (1, "email taken", usersOfApp);
        } else {
            userRepository.save(usersOfApp);
            return new UserRegistrationDataRequest
                    (0, "registration was successful", usersOfApp);
        }
    }

    public UserRegistrationDataRequest checkUser(UserRegistrationData usersOfApp)
            throws IllegalAccessException {
        Optional<UserRegistrationData> userOptional =
                userRepository.findUserByEmail(usersOfApp.getEmail());

        if (!userOptional.isPresent()) {
            return new UserRegistrationDataRequest
                    (1, "student with email " + usersOfApp.getEmail() + " doesn't exist", usersOfApp);
        }

        UserRegistrationData usersOfAppOnBD = userOptional.get();
        if (!usersOfAppOnBD.getPassword().equals(usersOfApp.getPassword())) {
            return new UserRegistrationDataRequest
                    (2, "wrong password", usersOfApp);
        }


        return new UserRegistrationDataRequest
                (0, "authentication was successful", usersOfApp);
    }


}
