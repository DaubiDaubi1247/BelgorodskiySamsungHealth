package com.example.reg3.dao;


import lombok.*;

import javax.persistence.*;

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
    private Long id;

    @Column(name = "number_of_day", nullable = false)
    private Integer numberOfDay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id",
    referencedColumnName = "training_id")
    private Training training;

}
