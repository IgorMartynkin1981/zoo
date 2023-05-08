package ru.jetlyn.zoo.animal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jetlyn.zoo.enums.Species;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalInfo {

    private String name;
    private Species species;
    private List<AnimalFoodNorm> dietList;


    public AnimalInfo(String name, Species species) {
        this.name = name;
        this.species = species;
    }
}
