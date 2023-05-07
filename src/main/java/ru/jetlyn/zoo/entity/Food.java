package ru.jetlyn.zoo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность Продукты
 * Состоит из полей:
 * @name Название,
 * @amount Текущее количество,
 * @measure Единица измерения(кг/шт/л),
 * @typeOfProduct Тип (овощ/фрукт/мясо/жидкость).
 */

@Data
@Entity
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private double amound;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Measure measure;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_product", nullable = false)
    private TypeOfProduct typeOfProduct;

    @OneToMany(mappedBy = "food")
    List<Diet> ratings;
}
