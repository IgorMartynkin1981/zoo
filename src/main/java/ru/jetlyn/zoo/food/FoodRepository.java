package ru.jetlyn.zoo.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище сущностей Food
 */

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
