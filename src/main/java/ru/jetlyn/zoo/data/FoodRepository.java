package ru.jetlyn.zoo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jetlyn.zoo.entity.Food;

/**
 * Хранилище сущностей Food
 */

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
