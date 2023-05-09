package ru.jetlyn.zoo.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для сущности Food
 *
 * @getAllFoods - получить все продукты GET:{{baseUrl}}/zoo/foods
 * @getFood - получить продукт по его Id
 * @saveFood - создать/сохранить продукт POST:{{baseUrl}}/zoo/foods @RequestBody в формате JSON
 * @updateFood - обновить данные продукта по его Id PUT:{{baseUrl}}/zoo/foods @RequestBody в формате JSON
 * @deleteFoodById - удалить продукт по его Id DELETE:{{baseUrl}}/zoo/foods/:id
 * @deleteFoodByIds - удалить список продуктов по их Id DELETE:{{baseUrl}}/zoo/foods/group/:Ids
 * @deleteAllFood - удалить все продукты из хранилища DELETE:{{baseUrl}}/zoo/foods
 */

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

    @DeleteMapping("/foods/group/{ids}")
    public ResponseEntity<String> deleteAnimalByIds(@PathVariable List<Long> ids) {
        foodService.deleteFoodByIds(ids);
        return new ResponseEntity<>("Foods was deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/foods")
    public ResponseEntity<String> deleteAllAnimal() {
        foodService.deleteAllFood();
        return new ResponseEntity<>("All foods was deleted!", HttpStatus.OK);
    }

}
