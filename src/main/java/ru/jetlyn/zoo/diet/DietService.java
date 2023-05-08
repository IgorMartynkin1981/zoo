package ru.jetlyn.zoo.diet;

import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;
import ru.jetlyn.zoo.food.Food;

import java.util.Collection;
import java.util.List;

public interface DietService {

    Collection<Diet> getAll();

    Collection<AnimalInfo> getAllDietsAnimal(Species species, String predator,
                                             TypeOfProduct typeOfProduct, String name,
                                             Integer from, Integer size);

    Diet getDietById(DietId dietId);

    Diet saveDietAnimal(Diet diet);

    Diet updateDietAnimal(Diet diet);

    Collection<AnimalInfo> saveDietsAnimal(Animal animal, List<Food> foodList, double amount);

}
