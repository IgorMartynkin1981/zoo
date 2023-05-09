package ru.jetlyn.zoo.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.jetlyn.zoo.diet.Diet;
import ru.jetlyn.zoo.enums.Measure;
import ru.jetlyn.zoo.enums.TypeOfProduct;

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

    @Transient
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Diet> dietList;
}
