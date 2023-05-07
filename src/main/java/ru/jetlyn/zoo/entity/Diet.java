package ru.jetlyn.zoo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *
 */

@Data
@Entity
@Table(name = "diet")
public class Diet {

    @EmbeddedId
    private DietId dietId;

    @ManyToOne
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;



    @Column(nullable = false)
    private double amount;
}
