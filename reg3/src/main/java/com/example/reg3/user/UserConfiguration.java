package com.example.reg3.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository) {
        return args -> {
            UsersOfApp admin1 = new UsersOfApp(
                    "Sania S.",
                    "admin1@gmaio.com",
                    "admin1"
            );

            UsersOfApp admin2 = new UsersOfApp(
                    "Sania B.",
                    "admin2@gmaio.com",
                    "admin2");

                    repository.saveAll(
                            List.of(admin1, admin2)
            );

        };
    }


}
