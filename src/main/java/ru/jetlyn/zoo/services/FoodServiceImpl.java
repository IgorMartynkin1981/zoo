package ru.jetlyn.zoo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.data.AnimalRepository;
import ru.jetlyn.zoo.data.FoodRepository;
import ru.jetlyn.zoo.entity.Animal;
import ru.jetlyn.zoo.entity.Food;

import java.util.List;

/**
 * Сервис по работе с методами для сущностей Food
 * @getAllFoods получить из хранилища всех животных
 * @saveFood сохранить в хранилище животное
 */

@Service
public class FoodServiceImpl {

    private FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods () {
        return foodRepository.findAll();
    }

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }
}
