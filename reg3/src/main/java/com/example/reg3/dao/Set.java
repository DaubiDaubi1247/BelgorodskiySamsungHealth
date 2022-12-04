package com.example.reg3.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "set_id", nullable = false)
    private Long id;

    @Column(name = "number_of_repetitions", nullable = false)
    private Integer numberOfRepetitions;


    @Column(name = "rest_time", nullable = false)
    @Temporal(TemporalType.TIME)
    Date restTime;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    Exercise exercise;
}
