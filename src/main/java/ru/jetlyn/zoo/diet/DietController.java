package ru.jetlyn.zoo.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;
import ru.jetlyn.zoo.food.dto.FoodDietInfo;

import java.util.Collection;
import java.util.List;

/**
 * Контроллер по работе с методами для сущностей Diet
 *
 * @getAll() - получить меню животных. GET:{{baseUrl}}/zoo/diets
 * @getAllDietsAnimal - Получить информацию о животных, с перечислением продуктов их питания.
 * GET:{{baseUrl}}/zoo/diets/all?name=Т
 * @getDietById - получить ежедневную норму для животного по его id.
 * GET:{{baseUrl}}/zoo/diets/diet/:animalId/:foodid
 * @saveDietAnimal - Назначение продукта животному. POST:{{baseUrl}}/zoo/diets/diet/:animalId/:foodid/:amound
 * @updateDietAnimal - Изменение ежедневной нормы для животного по его id.
 * PUT:{{baseUrl}}/zoo/diets/diet/:animalId/:foodid/:amound
 * @informationDiet - Получить информацию о питании животных на 7 дней от заданной даты.
 * GET:{{baseUrl}}/zoo/diets/info?periodStart=2023-05-20
 */

@RestController
@RequestMapping(path = "/zoo")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping(value = "/diets")
    public Collection<Diet> getAll() {
        return dietService.getAll();
    }

    @GetMapping(value = "/diets/all")
    @ResponseBody
    public Collection<AnimalInfo> getAll(@RequestParam(name = "species", required = false) Species species,
                                         @RequestParam(name = "predator", required = false) String predator,
                                         @RequestParam(name = "typeOfProduct", required = false)
                                         TypeOfProduct typeOfProduct,
                                         @RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "from", defaultValue = "0") Integer from,
                                         @RequestParam(name = "size", defaultValue = "20") Integer size) {

        return dietService.getAllDietsAnimal(species, predator, typeOfProduct, name, from, size);
    }

    @GetMapping(value = "/diets/diet/{animalId}/{foodId}")
    public Diet getDietById(@PathVariable long animalId, @PathVariable long foodId) {
        return dietService.getDietById(animalId, foodId);
    }

    @PostMapping(value = "/diets/diet/{animalId}/{foodId}/{amount}")
    public Diet saveDiet(@PathVariable long animalId, @PathVariable long foodId, @PathVariable long amount) {
        return dietService.saveDietAnimal(animalId, foodId, amount);
    }

    @PutMapping(value = "/diets/diet/{animalId}/{foodId}/{amount}")
    public Diet updateDietById(@PathVariable long animalId, @PathVariable long foodId, @PathVariable long amount) {
        return dietService.updateDietAnimal(animalId, foodId, amount);
    }

    @GetMapping(value = "/diets/info")
    public List<FoodDietInfo> informationDiet(@RequestParam(name = "periodStart", required = false) String periodStart) {
        return dietService.informationDiet(periodStart);
    }

}
