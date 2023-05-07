package ru.jetlyn.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.entity.Food;
import ru.jetlyn.zoo.services.FoodService;

import java.util.List;

@RestController
@RequestMapping(path = "/zoo")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping(value = "/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping(value = "/foods/{id}")
    public Food getAnimal(@PathVariable long id) {
        return foodService.getFood(id);
    }

    @PostMapping(value = "/foods")
    public Food createAnimal(@RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @PutMapping(value = "/foods")
    public Food updateAnimal(@RequestBody Food food) {
        return foodService.updateFood(food);
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteAnimalById(@PathVariable Long id) {
        foodService.deleteFoodById(id);
        return new ResponseEntity<>(String.format("Food with ID: %s was deleted!", id), HttpStatus.OK);
    }

    @DeleteMapping("/foods/group")
    public ResponseEntity<String> deleteAnimalByIds(@RequestBody List<Long> foodIds) {
        foodService.deleteFoodByIds(foodIds);
        return new ResponseEntity<>("Foods was deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/foods/all")
    public ResponseEntity<String> deleteAllAnimal() {
        foodService.deleteAllFood();
        return new ResponseEntity<>("All foods was deleted!", HttpStatus.OK);
    }

}
