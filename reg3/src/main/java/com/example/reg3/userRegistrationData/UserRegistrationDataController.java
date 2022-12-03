package com.example.reg3.userRegistrationData;

import com.example.reg3.requastion.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("hole")
public class UserRegistrationDataController {
    private final UserRegistrationDataService userRegistrationDataService;

    @Autowired
    public UserRegistrationDataController(UserRegistrationDataService userService) {
        this.userRegistrationDataService = userService;
    }

    @GetMapping
    public List<UserRegistrationData> getStudents() {
        return userRegistrationDataService.getUsers();
    }

    @PostMapping("registration")
    public Request registrationUser(@RequestBody UserRegistrationData user)
            throws IllegalAccessException {
        return userRegistrationDataService.addNewUser(user);
    }

    @PostMapping("authentication")
    public Request authenticationUser(@RequestBody UserRegistrationData user)
            throws IllegalAccessException {
        return userRegistrationDataService.checkUser(user);
    }

}