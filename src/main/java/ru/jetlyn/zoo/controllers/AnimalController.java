package ru.jetlyn.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.entity.Animal;
import ru.jetlyn.zoo.services.AnimalService;

import java.util.List;

@RestController
@RequestMapping(path = "/zoo")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "/animals")
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping(value = "/animals/{id}")
    public Animal getAnimal(@PathVariable long id) {
        return animalService.getAnimal(id);
    }

    @PostMapping(value = "/animals")
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @PutMapping(value = "/animals")
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<String> deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
        return new ResponseEntity<>(String.format("Animal with ID: %s was deleted!", id), HttpStatus.OK);
    }

    @DeleteMapping("/animals/group")
    public ResponseEntity<String> deleteAnimalByIds(@RequestBody List<Long> animalIds) {
        animalService.deleteAnimalByIds(animalIds);
        return new ResponseEntity<>("Animals was deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/animals/all")
    public ResponseEntity<String> deleteAllAnimal() {
        animalService.deleteAllAnimal();
        return new ResponseEntity<>("All animals was deleted!", HttpStatus.OK);
    }
}
