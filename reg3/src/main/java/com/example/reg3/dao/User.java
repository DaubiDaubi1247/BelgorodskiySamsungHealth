package com.example.reg3.dao;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_of_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "weight")
    private  Float weight;

    @Column(name = "height")
    private Integer height;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_progress_in_training_id",
            referencedColumnName = "user_progress_in_training_id")
    private UserProgressInTraining userProgresInTraining;


    public User(String name,
                Float weight,
                Integer height,
                UserProgressInTraining userProgresInTraining) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.userProgresInTraining = userProgresInTraining;
    }
}
