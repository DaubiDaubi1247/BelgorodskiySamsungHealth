package com.example.reg3.requastion;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DietQuary {

    private String label;

    private String description;

    private String status;

    private List<String> dishes;

    public DietQuary(String label,
                     String description,
                     String status,
                     List<String> dishes) {
        this.label = label;
        this.description = description;
        this.status = status;
        this.dishes = dishes;
    }
}
