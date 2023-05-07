package ru.jetlyn.zoo.services;

import ru.jetlyn.zoo.entity.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAllAnimals();

    Animal getAnimal(long id);

    Animal saveAnimal(Animal animal);

    Animal updateAnimal(Animal animal);

}
