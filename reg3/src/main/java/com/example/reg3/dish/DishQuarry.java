package com.example.reg3.dish;


import lombok.*;

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
}
