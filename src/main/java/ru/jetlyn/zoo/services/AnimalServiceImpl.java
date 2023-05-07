package ru.jetlyn.zoo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.data.AnimalRepository;
import ru.jetlyn.zoo.entity.Animal;
import ru.jetlyn.zoo.exception.DataNotFound;

import java.util.List;

/**
 * Сервис по работе с методами для сущностей Animal
 * @getAllAnimals получить из хранилища всех животных
 * @saveAnimal сохранить в хранилище животное
 */

@Service
public class AnimalServiceImpl implements AnimalService{

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> getAllAnimals () {
        return animalRepository.findAll();
    }

    @Override
    public Animal getAnimal(long id) {
        return animalRepository.findById(id).orElseThrow(() -> new DataNotFound(
                String.format("Animal with id %d was not found in the database")));
    }

    @Override
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(getAnimal(animal.getId()));
    }
}
