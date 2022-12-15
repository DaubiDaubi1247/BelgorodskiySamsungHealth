package com.example.reg3.diet;


import com.example.reg3.dish.Dish;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DietQuary {

    private String label;

    private String description;

    private String status;

    private List<String> dishes;

}
