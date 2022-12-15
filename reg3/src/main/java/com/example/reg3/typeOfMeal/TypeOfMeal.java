package com.example.reg3.typeOfMeal;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "type_of_meal")
public class TypeOfMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_of_meal_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false,
            unique = true, length = 20)
    private String label;
}
