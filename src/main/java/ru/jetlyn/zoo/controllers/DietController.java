package ru.jetlyn.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.entity.Diet;
import ru.jetlyn.zoo.entity.DietId;
import ru.jetlyn.zoo.services.DietService;

import java.util.List;

@RestController
@RequestMapping(path = "/zoo")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping(value = "/diets")
    public List<Diet> getAllDiets() {
        return dietService.getAllDiet();
    }

    @GetMapping(value = "/diets/diet")
    public Diet getDietBy(@RequestBody DietId dietId) {
        return dietService.getDietBy(dietId);
    }

    @GetMapping(value = "/diets/animal/{animalId}")
    public List<Diet> getDietAnimal(@PathVariable long animalId) {
        return dietService.getDietAnimalById(animalId);
    }

    @GetMapping(value = "/diets/food/{foodId}")
    public List<Diet> getDietFood(@PathVariable long foodId) {
        return dietService.getDietFoodById(foodId);
    }
}
