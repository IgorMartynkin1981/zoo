package ru.jetlyn.zoo.diet;

import ru.jetlyn.zoo.diet.Diet;
import ru.jetlyn.zoo.diet.DietId;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;

import java.util.List;

public interface DietService {

    List<Diet> getAllDiet();

    List<AnimalInfo> getDietsAnimal();

    Diet getDietBy(DietId dietId);

    List<Diet> getDietAnimalById(long animalId);

    List<Diet> getDietFoodById(long foodId);

    Diet saveDietAnimal(Diet diet);

    Diet updateDietAnimal(Diet diet);

    void deleteAnimal(long animalId);

    void deleteFood(long foodId);

    void deleteAll();

}
