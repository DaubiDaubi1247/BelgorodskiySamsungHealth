package com.example.reg3.dao;


import lombok.*;

import javax.persistence.*;

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


}
