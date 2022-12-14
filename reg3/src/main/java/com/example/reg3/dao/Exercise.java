package com.example.reg3.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id", nullable = false)
    private Long id;

    @Column(name = "label", nullable = false,
            unique = true, length = 20)
    private String label;

    @Column(name = "description")
    private String description;

    public Exercise(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
