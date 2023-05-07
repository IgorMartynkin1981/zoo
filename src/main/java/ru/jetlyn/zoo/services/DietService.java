package ru.jetlyn.zoo.services;

import ru.jetlyn.zoo.entity.Diet;
import ru.jetlyn.zoo.entity.DietId;

import java.util.List;

public interface DietService {

    List<Diet> getAllDiet();

    Diet getDietBy(DietId dietId);

    List<Diet> getDietAnimalById(long animalId);

    List<Diet> getDietFoodById(long foodId);

    Diet saveDietAnimal(Diet diet);

    Diet updateDietAnimal(Diet diet);

    void deleteAnimal(long animalId);

    void deleteFood(long foodId);

    void deleteAll();

}
