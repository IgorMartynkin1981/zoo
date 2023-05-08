package ru.jetlyn.zoo.animal;

import ru.jetlyn.zoo.animal.Animal;

import java.util.List;

/**
 * Контракт для сущности Animal
 *
 * @getAllAnimals - получить всех животных
 * @getAnimal - получить животное по его Id
 * @saveAnimal - создать/сохранить животное
 * @updateAnimal - обновить данные животного, нужно удалить нет в задании
 * @deleteAnimalById - удалить животное по его Id
 * @deleteAnimalByIds - удалить список животных по их Id
 * @deleteAllAnimal - удалить всех животных из хранилища
 */

public interface AnimalService {

    List<Animal> getAllAnimals();

    Animal getAnimal(long id);

    Animal saveAnimal(Animal animal);

    @Deprecated(since = "Next iteration")
    Animal updateAnimal(Animal animal);

    void deleteAnimalById(long id);

    void deleteAnimalByIds(List<Long> animalIds);

    void deleteAllAnimal();

}
