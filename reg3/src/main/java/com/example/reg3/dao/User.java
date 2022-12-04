package com.example.reg3.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_of_app")
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

    @Column(name = "day_of_training")
    private Integer dayOfTraining;

    @Column(name = "training_id")
    private Long trainingId;

    @Column(name = "diet_id")
    private Long dietId;

}
