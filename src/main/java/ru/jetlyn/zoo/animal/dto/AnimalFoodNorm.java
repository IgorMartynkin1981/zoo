package ru.jetlyn.zoo.animal.dto;

import lombok.Data;
import ru.jetlyn.zoo.enums.Measure;

/**
 * Данный класс необходим для формирования класса AnimalInfo
 */
@Data
public class AnimalFoodNorm {
    private String name;
    private double amound;
    private Measure measure;
}
