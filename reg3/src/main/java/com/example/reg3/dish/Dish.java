package com.example.reg3.dish;


import com.example.reg3.meal.MealTime;
import com.example.reg3.typeOfMeal.TypeOfMeal;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany()
    @JoinTable(name = "mealtime_dish_map",
            joinColumns = @JoinColumn(
                    name = "dish_id",
                    referencedColumnName = "dish_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "meal_time_id",
                    referencedColumnName = "meal_time_id")
    )
    private List<MealTime> mealTimes;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_of_meal_id")
    private TypeOfMeal type;

    public void setParamsWithOutMealTime(DishQuarry dish) {
        this.id = null;
        this.label = dish.getLabel();
        this.calPerGram = dish.getCalPerGram();
        this.carbsPerGram = dish.getCarbsPerGram();
        this.fatsPerGram =dish.getFatsPerGram();
        this.proteinsPerGram = dish.getProteinsPerGram();
    }
}
