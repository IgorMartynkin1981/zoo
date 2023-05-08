package ru.jetlyn.zoo.animal.dto;

import ru.jetlyn.zoo.diet.Diet;

public class AnimalMapper {

    public static AnimalInfo toAnimalInfo(Diet diet) {
        AnimalInfo animalInfo = new AnimalInfo();
        animalInfo.setName(diet.getAnimal().getName());
        animalInfo.setSpecies(diet.getAnimal().getSpecies());

        return animalInfo;
    }

    public static AnimalFoodNorm toAnimalFoodNorm(Diet diet) {
        AnimalFoodNorm animalFoodNorm = new AnimalFoodNorm();
        animalFoodNorm.setName(diet.getFood().getName());
        animalFoodNorm.setAmound(diet.getAmount());
        animalFoodNorm.setMeasure(diet.getFood().getMeasure());

        return animalFoodNorm;
    }

}
