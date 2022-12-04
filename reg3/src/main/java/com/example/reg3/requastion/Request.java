package com.example.reg3.requastion;

import com.example.reg3.dao.UserRegistrationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Integer status;
    private String message;
    UserRegistrationData usersOfApp;


    public Request(UserRegistrationData usersOfApp) {
        this.usersOfApp = usersOfApp;
    }
}
