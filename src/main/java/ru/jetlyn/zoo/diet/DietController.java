package ru.jetlyn.zoo.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;

import java.util.Collection;

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

}
