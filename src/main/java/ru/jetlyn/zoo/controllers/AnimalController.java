package ru.jetlyn.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jetlyn.zoo.entity.Animal;
import ru.jetlyn.zoo.services.AnimalService;

import java.util.List;

@RestController
@RequestMapping(path ="/zoo")
public class AnimalController {

    AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "/animals")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animal = animalService.getAllAnimals();
        return animal != null && !animal.isEmpty()
                ? new ResponseEntity<>(animal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/animals/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable long id) {
        Animal animal = animalService.getAnimal(id);
        return animal != null
                ? new ResponseEntity<>(animal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "zoo/animals")
    public Animal create(@RequestBody Animal animal) {
        //animalService.saveAnimal(animal);
        return animalService.saveAnimal(animal);
//                ? new ResponseEntity<>(create, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "/animals/{id}")
    public Animal update(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }
}
