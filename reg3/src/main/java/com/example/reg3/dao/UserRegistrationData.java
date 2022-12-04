package com.example.reg3.dao;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_registration_data")
public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_registration_data_id",
            nullable = false)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id")
    private User user;


    public UserRegistrationData(String login,
                                String email,
                                String password,
                                Boolean isAdmin,
                                User user) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.user = user;
    }
}

























