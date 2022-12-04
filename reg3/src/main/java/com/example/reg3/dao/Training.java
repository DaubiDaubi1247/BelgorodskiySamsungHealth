package com.example.reg3.dao;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false, length = 20, unique = true)
    private String label;

    @Column(name = "count_of_days", nullable = false)
    private Integer countOfDays;


}
