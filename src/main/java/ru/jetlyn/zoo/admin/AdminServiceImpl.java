package ru.jetlyn.zoo.admin;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.animal.AnimalRepository;
import ru.jetlyn.zoo.diet.Diet;
import ru.jetlyn.zoo.diet.DietRepository;
import ru.jetlyn.zoo.food.Food;
import ru.jetlyn.zoo.food.FoodRepository;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * Сервис для заполнения БД тестовыми данными
 *
 *  GET:{{baseUrl}}/zoo/diets
 */

@Service
public class AdminServiceImpl implements AdminService {

    public final AnimalRepository animalRepository;
    public final FoodRepository foodRepository;
    public final DietRepository dietRepository;

    @Autowired
    public AdminServiceImpl(AnimalRepository animalRepository, FoodRepository foodRepository, DietRepository dietRepository) {
        this.animalRepository = animalRepository;
        this.foodRepository = foodRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    public String addTestDb() {
        try {
            animalRepository.saveAll(getAnimal());
            foodRepository.saveAll(getFood());
            dietRepository.saveAll(getDiet());
        } catch (IOException e) {
            throw new RuntimeException("Что то пошло не так!");
        }

        return "База заполнена!!!";
    }

    public List<Animal> getAnimal() throws IOException {
        InputStream inputStream = Resources.getResource("Animals.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Animal>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    public List<Food> getFood() throws IOException {
        InputStream inputStream = Resources.getResource("Food.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Food>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    public List<Diet> getDiet() throws IOException {
        InputStream inputStream = Resources.getResource("Diet.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Diet>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }
}
