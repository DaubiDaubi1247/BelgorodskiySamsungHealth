package com.example.reg3.diet;


import com.example.reg3.dish.Dish;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false,
            unique = true, length = 20)
    private String label;

    @Column(name = "description")
    private String description;

    @ManyToMany()
    @JoinTable(name = "diet_dish_map",
            joinColumns = @JoinColumn(
                    name = "diet_id",
                    referencedColumnName = "diet_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "dish_id",
                    referencedColumnName = "dish_id")
    )
    private List<Dish> dishes;


}
