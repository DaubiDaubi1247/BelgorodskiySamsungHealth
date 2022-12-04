package com.example.reg3;

import com.example.reg3.dao.User;
import com.example.reg3.dao.UserRegistrationData;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class AddTestInfo {

    //todo добавить Training
    static List<User> userList = List.of(
            new User("Саня", 68.23f,
                    null, null, null),
            new User("Саня", 76.24f,
                    178, null, null)

    );
//todo
    static List<UserRegistrationData> userRegistrationDataList = List.of(
            new UserRegistrationData("monokas", "monokas@gmail.com",
                    "monokas", true, userList.get(0))
    );




//    @Test
//    public void addUsers() {
//
//        repository.saveAll(contracts);
//
//    }
}
