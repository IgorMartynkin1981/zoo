package ru.jetlyn.zoo.diet;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.food.Food;

import javax.persistence.*;

/**
 * * Сущность Питание
 *  * Состоит из полей:
 *  * @dietId составной ключ
 *  * @animal сущность Animal
 *  * @food сущность Food
 *  * @amount объём на сутки
 */

@Data
@Entity
@Table(name = "diet")
public class Diet {

    @EmbeddedId
    private DietId dietId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Animal animal;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    @Column(nullable = false)
    private double amount;
}
