package com.example.reg3.dao.Food;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false,
            unique = true, length = 20)
    private String label;

    @Column(name = "cals_per_gram")
    private Integer calPerGram;

    @Column(name = "proteins_per_gram")
    private Float proteinsPerGram;

    @Column(name = "fats_per_gram")
    private Float fatsPerGram;

    @Column(name = "carbs_per_gram")
    private Float carbsPerGram;
}
