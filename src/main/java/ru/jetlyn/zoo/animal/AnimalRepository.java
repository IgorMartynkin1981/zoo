package ru.jetlyn.zoo.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище сущностей Animal
 */

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
