package com.example.reg3.dao;

import com.example.reg3.diet.Diet;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_of_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "completedTrainers")
    private Integer countOfCompletedTrainers;

    @OneToOne()
    @JoinColumn(name = "diet_id",
            referencedColumnName = "diet_id")
    private Diet diet;

    @OneToOne()
    @JoinColumn(name = "user_progress_in_training_id",
            referencedColumnName = "user_progress_in_training_id")
    private UserProgressInTraining userProgresInTraining;


    public User(String name,
                Float weight,
                Integer height,
                UserProgressInTraining userProgresInTraining,
                Integer countOfCompletedTrainers) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.userProgresInTraining = userProgresInTraining;
        this.countOfCompletedTrainers = countOfCompletedTrainers;
    }

    public void incCountOfTrainigs() {
        countOfCompletedTrainers++;
    }

}

