package ru.jetlyn.zoo.diet;

import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;

import java.util.Collection;
import java.util.List;

public interface DietService {

    List<Diet> getAllDiet();

    Collection<AnimalInfo> getAllDietsAnimal(Species species, String predator,
                                             TypeOfProduct typeOfProduct, String name,
                                             Integer from, Integer size);

    Diet getDietBy(DietId dietId);

    List<Diet> getDietAnimalById(long animalId);

    List<Diet> getDietFoodById(long foodId);

    Diet saveDietAnimal(Diet diet);

    Diet updateDietAnimal(Diet diet);

    void deleteAnimal(long animalId);

    void deleteFood(long foodId);

    void deleteAll();

}
