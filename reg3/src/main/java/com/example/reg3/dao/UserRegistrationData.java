package com.example.reg3.dao;


import com.example.reg3.dao.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationData {
    @Id//зачем он здесь гененрирует значение
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_registration_data_id", nullable = false)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id")
    private User transports;


    public UserRegistrationData(String name,
                                String email,
                                String password) {
        this.login = name;
        this.email = email;
        this.password = password;
    }
}
