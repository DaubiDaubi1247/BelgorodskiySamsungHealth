package com.example.reg3.user;

import com.example.reg3.requastion.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hole")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UsersOfApp> getStudents() {
        return userService.getUsers();
    }

    @PostMapping("registration")
    public Request registrationUser(@RequestBody UsersOfApp user)
            throws IllegalAccessException {
        return userService.addNewUser(user);
    }

    @PostMapping("authentication")
    public Request authenticationUser(@RequestBody UsersOfApp user)
            throws IllegalAccessException {
        return userService.checkUser(user);
    }

}
