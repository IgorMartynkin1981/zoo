package ru.jetlyn.zoo.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.exception.DataNotFound;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис по работе с методами для сущностей Food
 *
 * @getAllFoods - получить все продукты
 * @getFood - получить продукт по его Id
 * @saveFood - создать/сохранить продукт
 * @updateFood - обновить данные продукта
 * @deleteFoodById - удалить продукт по его Id
 * @deleteFoodByIds - удалить список продуктов по их Id
 * @deleteAllFood - удалить все продукты из хранилища
 */

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFood(long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Food with id %d was not found in the database"));
    }

    @Override
    public Food saveFood(@Valid Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(long foodId, double amound) {
        Food food = getFood(foodId);
        food.setAmound(amound);
        return foodRepository.save(food);
    }

    @Override
    public void deleteFoodById(long id) {
        getFood(id);
        foodRepository.deleteById(id);
    }

    @Override
    public void deleteFoodByIds(List<Long> foodIds) {
        foodRepository.deleteAllById(foodIds);
    }

    @Override
    public void deleteAllFood() {
        foodRepository.deleteAll();
    }
}
