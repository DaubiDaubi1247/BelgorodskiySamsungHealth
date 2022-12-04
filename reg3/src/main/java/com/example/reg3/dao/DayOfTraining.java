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
@Table(name = "days_of_training")
public class DayOfTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "days_of_training_id", nullable = false)
    private Long id;

    @Column(name = "number_of_day", nullable = false)
    private Integer numberOfDay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id",
    referencedColumnName = "training_id")
    private Training training;


    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "set_day_map",
            joinColumns = @JoinColumn(
                    name = "days_of_training_id",
                    referencedColumnName = "days_of_training_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "set_id",
                    referencedColumnName = "set_id"
            )
    )
    private List<Set> sets;

    public DayOfTraining(Integer numberOfDay, Training training, List<Set> sets) {
        this.numberOfDay = numberOfDay;
        this.training = training;
        this.sets = sets;
    }
}
