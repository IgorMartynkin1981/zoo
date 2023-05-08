package ru.jetlyn.zoo.animal.dto;

import ru.jetlyn.zoo.diet.Diet;

/**
 * Маппер для сущности Animal
 * приводит форму Diet из базы в форму AnimalFoodNorm
 */

public class AnimalMapper {

    public static AnimalFoodNorm toAnimalFoodNorm(Diet diet) {
        AnimalFoodNorm animalFoodNorm = new AnimalFoodNorm();
        animalFoodNorm.setName(diet.getFood().getName());
        animalFoodNorm.setAmound(diet.getAmount());
        animalFoodNorm.setMeasure(diet.getFood().getMeasure());

        return animalFoodNorm;
    }

}
