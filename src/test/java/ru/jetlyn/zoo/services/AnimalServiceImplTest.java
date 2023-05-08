package ru.jetlyn.zoo.services;

import org.junit.jupiter.api.Test;
import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.diet.Diet;
import ru.jetlyn.zoo.enums.Measure;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;
import ru.jetlyn.zoo.animal.AnimalServiceImpl;
import ru.jetlyn.zoo.food.Food;
import ru.jetlyn.zoo.food.FoodServiceImpl;

import java.util.ArrayList;
import java.util.List;

class AnimalServiceImplTest {
    AnimalServiceImpl animalService;
    FoodServiceImpl foodService;

    public AnimalServiceImplTest(AnimalServiceImpl animalService, FoodServiceImpl foodService) {
        this.animalService = animalService;
        this.foodService = foodService;
    }

    void createAnimal() {
        //Обезьяна (банан, яблоко)
        Food food1 = new Food();
        food1.setName("банан");
        food1.setAmound(10);
        food1.setMeasure(Measure.pcs);
        food1.setTypeOfProduct(TypeOfProduct.FRUIT);

        Food food2 = new Food();
        food2.setName("яблоко");
        food2.setAmound(100);
        food2.setMeasure(Measure.kg);
        food2.setTypeOfProduct(TypeOfProduct.FRUIT);

        Animal animal1 = new Animal();
        animal1.setName("Обезьяна");
        animal1.setSpecies(Species.MAMMAL);
        animal1.setPredator(false);

    }

    @Test
    void saveAnimal() {
        Food food1 = new Food();
        food1.setName("банан");
        food1.setAmound(10);
        food1.setMeasure(Measure.pcs);
        food1.setTypeOfProduct(TypeOfProduct.FRUIT);
        food1 = foodService.saveFood(food1);

        Food food2 = new Food();
        food2.setName("яблоко");
        food2.setAmound(100);
        food2.setMeasure(Measure.kg);
        food2.setTypeOfProduct(TypeOfProduct.FRUIT);
        food2 = foodService.saveFood(food2);


        Animal animal1 = new Animal();
        animal1.setName("Обезьяна");
        animal1.setSpecies(Species.MAMMAL);
        animal1.setPredator(false);
        animal1 = animalService.saveAnimal(animal1);

        Diet diet1 = new Diet();
        diet1.setAnimal(animal1);
        diet1.setFood(food1);
        diet1.setAmount(23.0);


        Diet diet2 = new Diet();
        diet2.setAnimal(animal1);
        diet2.setFood(food2);
        diet2.setAmount(23.0);

        List<Diet> dietList = new ArrayList<>();
        dietList.add(diet1);
        dietList.add(diet2);
        
        animalService.saveAnimal(animal1);

        System.out.println(animalService.getAllAnimals());




    }
}