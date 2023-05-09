package ru.jetlyn.zoo.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;

import java.util.Collection;
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

    @GetMapping(value = "/diets/diet")
    public Diet getDietById(@RequestBody DietId dietId) {
        return dietService.getDietById(dietId);
    }

}
