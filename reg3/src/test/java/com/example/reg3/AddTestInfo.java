package com.example.reg3;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddTestInfo {

    @Test
    public void addValuesCascade() {

        repository.saveAll(contracts);

    }
}
