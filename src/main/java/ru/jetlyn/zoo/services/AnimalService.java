package ru.jetlyn.zoo.services;

import ru.jetlyn.zoo.entity.Animal;

import java.util.List;

/**
 * Контракт для сущности Animal
 *
 * @getAllAnimals - получить всех животных
 * @getAnimal - получить животное по его Id
 * @saveAnimal - создать/сохранить животное
 * @updateAnimal - обновить данные животного
 * @deleteAnimalById - удалить животное по его Id
 * @deleteAnimalByIds - удалить список животных по их Id
 * @deleteAllAnimal - удалить всех животных из хранилища
 */

public interface AnimalService {

    List<Animal> getAllAnimals();

    Animal getAnimal(long id);

    Animal saveAnimal(Animal animal);

    Animal updateAnimal(Animal animal);

    void deleteAnimalById(long id);

    void deleteAnimalByIds(List<Long> animalIds);

    void deleteAllAnimal();

}
