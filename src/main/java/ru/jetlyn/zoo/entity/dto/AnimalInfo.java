package ru.jetlyn.zoo.entity.dto;

import lombok.Data;
import ru.jetlyn.zoo.entity.Diet;
import ru.jetlyn.zoo.entity.enums.Species;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class AnimalInfo {
    @Min(value = 3)
    private String name;
    private Species species;
    private boolean predator;
    List<Diet> ratings;
}
