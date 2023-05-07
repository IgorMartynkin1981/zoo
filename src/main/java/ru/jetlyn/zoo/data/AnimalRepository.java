package ru.jetlyn.zoo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jetlyn.zoo.entity.Animal;

/**
 * Хранилище сущностей Animal
 */

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
