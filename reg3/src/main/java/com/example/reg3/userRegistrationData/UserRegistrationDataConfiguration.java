//package com.example.reg3.userRegistrationData;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserRegistrationDataConfiguration {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            UserRegistrationDataRepository repository) {
//        return args -> {
//            UserRegistrationData admin1 = new UserRegistrationData(
//                    "Sania S.",
//                    "admin1@gmaio.com",
//                    "admin1"
//            );
//
//            UserRegistrationData admin2 = new UserRegistrationData(
//                    "Sania B.",
//                    "admin2@gmaio.com",
//                    "admin2");
//
//                    repository.saveAll(
//                            List.of(admin1, admin2)
//            );
//
//        };
//    }
//
//
//}
