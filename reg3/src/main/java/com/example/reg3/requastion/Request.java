package com.example.reg3.requastion;

import com.example.reg3.user.UsersOfApp;
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
    UsersOfApp usersOfApp;


    public Request(UsersOfApp usersOfApp) {
        this.usersOfApp = usersOfApp;
    }
}
