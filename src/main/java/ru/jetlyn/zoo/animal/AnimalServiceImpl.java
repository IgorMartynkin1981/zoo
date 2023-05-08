package ru.jetlyn.zoo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.animal.AnimalRepository;
import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.exception.DataNotFound;
import ru.jetlyn.zoo.animal.AnimalService;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис по работе с методами для сущностей Animal
 *
 * @getAllAnimals - получить всех животных
 * @getAnimal - получить животное по его Id
 * @saveAnimal - создать/сохранить животное
 * @updateAnimal - обновить данные животного
 * @deleteAnimalById - удалить животное по его Id
 * @deleteAnimalByIds - удалить список животных по их Id
 * @deleteAllAnimal - удалить всех животных из хранилища
 */

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

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
        return animalRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Animal with id %d was not found in the database"));
    }

    @Override
    public Animal saveAnimal(@Valid Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(@Valid Animal animal) {
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

        return animalFromStorage;
    }

}
