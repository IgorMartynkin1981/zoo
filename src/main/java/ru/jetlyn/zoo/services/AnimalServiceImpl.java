package ru.jetlyn.zoo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.data.AnimalRepository;
import ru.jetlyn.zoo.entity.Animal;
import ru.jetlyn.zoo.exception.DataNotFound;

import java.util.List;

/**
 * Сервис по работе с методами для сущностей Animal
 *
 * @getAllAnimals получить из хранилища всех животных
 * @saveAnimal сохранить в хранилище животное
 */

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> getAllAnimals() {
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
        return animalRepository.save(changeParamAnimal(animal));
    }

    @Override
    public void deleteAnimalById(long id) {
        getAnimal(id);
        animalRepository.deleteById(id);
    }

    @Override
    public void deleteAnimalByIds(List<Long> animalIds) {
        animalRepository.deleteAllById(animalIds);
    }

    @Override
    public void deleteAllAnimal() {
        animalRepository.deleteAll();
    }

    private Animal changeParamAnimal(Animal animal) {
        Animal animalFromStorage = getAnimal(animal.getId());
        if (!animal.getName().equals(animalFromStorage.getName())) animalFromStorage.setName(animal.getName());

        if (!animal.getSpecies().equals(animalFromStorage.getSpecies()))
            animalFromStorage.setSpecies(animal.getSpecies());

        if (animal.isPredator() != animalFromStorage.isPredator()) animalFromStorage.setPredator(animal.isPredator());

        if (animal.getRatings() != animalFromStorage.getRatings() || animal.getRatings().isEmpty())
            animalFromStorage.setRatings(animal.getRatings());

        return animalFromStorage;
    }

}
