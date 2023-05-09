package ru.jetlyn.zoo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для сущности Animal
 *
 * @getAllAnimals - получить всех животных GET:{{baseUrl}}/zoo/animals
 * @getAnimal - получить животное по его Id GET:{{baseUrl}}/zoo/animals/:id
 * @saveAnimal - создать/сохранить животное POST:{{baseUrl}}/zoo/animals @RequestBody в формате JSON
 * @deleteAnimalById - удалить животное по его Id DELETE:{{baseUrl}}/zoo/animals/:id
 * @deleteAnimalByIds - удалить список животных по их Id DELETE:{{baseUrl}}/zoo/animals/group/:Ids
 * @deleteAllAnimal - удалить всех животных из хранилища DELETE:{{baseUrl}}/zoo/animals
 */

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

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<String> deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
        return new ResponseEntity<>(String.format("Animal with ID: %s was deleted!", id), HttpStatus.OK);
    }

    @DeleteMapping("/animals/group/{ids}")
    public ResponseEntity<String> deleteAnimalByIds(@PathVariable List<Long> ids) {
        animalService.deleteAnimalByIds(ids);
        return new ResponseEntity<>("Animals was deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/animals")
    public ResponseEntity<String> deleteAllAnimal() {
        animalService.deleteAllAnimal();
        return new ResponseEntity<>("All animals was deleted!", HttpStatus.OK);
    }
}
