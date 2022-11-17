package com.example.reg3.user;

import com.example.reg3.requastion.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersOfApp> getUsers() {
        return userRepository.findAll();
    }

    public Request addNewUser(UsersOfApp usersOfApp)
            throws IllegalAccessException {
        Optional<UsersOfApp> userOptional =
                userRepository.findUsertByEmail(usersOfApp.getEmail());

        if (userOptional.isPresent()) {
            return new Request
                    (1, "email taken", usersOfApp);
        } else {
            userRepository.save(usersOfApp);
            return new Request
                    (0, "registration was successful", usersOfApp);
        }
    }

    public Request checkUser(UsersOfApp usersOfApp)
            throws IllegalAccessException {
        Optional<UsersOfApp> userOptional =
                userRepository.findUsertByEmail(usersOfApp.getEmail());

        if (!userOptional.isPresent()) {
            return new Request
                    (1, "student with email " + usersOfApp.getEmail() + " doesn't exist", usersOfApp);
        }

        UsersOfApp usersOfAppOnBD = userOptional.get();
        if (!usersOfAppOnBD.getPassword().equals(usersOfApp.getPassword())) {
            return new Request
                    (2, "wrong password", usersOfApp);
        }


        return new Request
                (0, "authentication was successful", usersOfApp);
    }


}
