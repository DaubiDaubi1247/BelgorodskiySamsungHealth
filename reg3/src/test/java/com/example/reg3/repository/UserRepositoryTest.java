package com.example.reg3.repository;

import com.example.reg3.requastion.ProgressOfUserWithPresent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void findProgressOfUser() {

//        var progres = (userRepository.findProgressOfUser(1L));
//

    }
}