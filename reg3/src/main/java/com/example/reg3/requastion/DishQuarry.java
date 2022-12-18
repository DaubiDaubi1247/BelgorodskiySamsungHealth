package com.example.reg3.requastion;


import com.example.reg3.dao.Dish;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DishQuarry {

    private String label;

    private String type;

    private Integer calPerGram;

    private Float proteinsPerGram;

    private Float fatsPerGram;

    private Float carbsPerGram;

    private List<String> mealTimes;

    public void setParamsFromEntity(Dish dish) {
        label = dish.getLabel();
        type = dish.getType().getLabel();
        calPerGram = dish.getCalPerGram();
        proteinsPerGram = dish.getProteinsPerGram();
        fatsPerGram = dish.getFatsPerGram();
        carbsPerGram = dish.getCarbsPerGram();
        mealTimes = new ArrayList<>();
        for (var mealTime: dish.getMealTimes()) {
            mealTimes.add(mealTime.getLabel());
        }
    }
}
