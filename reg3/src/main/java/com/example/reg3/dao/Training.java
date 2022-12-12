package com.example.reg3.dao;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trainings")
public class Training {


    enum Status {
        RED, GREEN, BLUE;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false, length = 20, unique = true)
    private String label;

    @Column(name = "count_of_days", nullable = false)
    private Integer countOfDays;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;


    @OneToMany(fetch = FetchType.LAZY)
    private List<DayOfTraining> daysOfTraining;


    public Training(String label,
                    Integer countOfDays,
                    String description,
                    String status,
                    List<DayOfTraining> daysOfTrainings) {
        this.label = label;
        this.countOfDays = countOfDays;
        this.description = description;
        this.status = status;
        this.daysOfTraining = daysOfTrainings;
    }
}
