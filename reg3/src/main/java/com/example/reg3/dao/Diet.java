package com.example.reg3.dao;


import com.example.reg3.requastion.DietQuary;
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

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "status")
    private String status;

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





    public void setIfoWthoutDishes(DietQuary dietQuary) {
        label = dietQuary.getLabel();
        description = dietQuary.getDescription();
        if (dietQuary.getStatus() != null){
            status = dietQuary.getStatus();
        } else {
            status = "available";
        }
    }





}
