package ru.jetlyn.zoo.services;

import ru.jetlyn.zoo.entity.Food;

import java.util.List;

/**
 * Контракт для сущности Food
 *
 * @getAllFoods - получить все продукты
 * @getFood - получить продукт по его Id
 * @saveFood - создать/сохранить продукт
 * @updateFood - обновить данные продукта по его Id
 * @deleteFoodById - удалить продукт по его Id
 * @deleteFoodByIds - удалить список продуктов по их Id
 * @deleteAllFood - удалить все продукты из хранилища
 */

public interface FoodService {

    List<Food> getAllFoods();

    Food getFood(long id);

    Food saveFood(Food food);

    Food updateFood(Food food);

    void deleteFoodById(long id);

    void deleteFoodByIds(List<Long> foodIds);

    void deleteAllFood();
}
