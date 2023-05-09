package ru.jetlyn.zoo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.jetlyn.zoo.exception.DataNotFound;

import java.util.List;

/**
 * Сервис по работе с методами для сущностей Animal
 *
 * @getAllAnimals - получить всех животных
 * @getAnimal - получить животное по его Id
 * @saveAnimal - создать/сохранить животное
 * @deleteAnimalById - удалить животное по его Id
 * @deleteAnimalByIds - удалить список животных по их Id
 * @deleteAllAnimal - удалить всех животных из хранилища
 */

@Service
@Validated
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
    public Animal saveAnimal(@Validated Animal animal) {
        return animalRepository.save(animal);
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

}
