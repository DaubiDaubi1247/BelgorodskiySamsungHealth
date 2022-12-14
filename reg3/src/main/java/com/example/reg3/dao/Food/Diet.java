package com.example.reg3.dao.Food;


import com.example.reg3.dao.Set;
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
    @Column(name = "diets_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false,
            unique = true, length = 20)
    private String label;

    @Column(name = "description")
    private String description;

    @ManyToMany()
    @JoinTable(name = "diet_dish_map",
            joinColumns = @JoinColumn(name = "diets_id", referencedColumnName = "diets_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "dish_id")
    )
    private List<Dish> dishes;


}
