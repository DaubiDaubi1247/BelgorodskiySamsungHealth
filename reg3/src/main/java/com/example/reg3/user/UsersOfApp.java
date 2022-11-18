package com.example.reg3.user;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersOfApp {
    @Id//зачем он здесь гененрирует значение
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String login;
    @Column(unique=true)
    private String email;
    private String password;

    private Boolean isAdmin;

    public UsersOfApp(String name,
                      String email,
                      String password) {
        this.login = name;
        this.email = email;
        this.password = password;
    }
}
