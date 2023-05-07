package ru.jetlyn.zoo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.data.DietRepository;
import ru.jetlyn.zoo.entity.Diet;
import ru.jetlyn.zoo.entity.DietId;
import ru.jetlyn.zoo.exception.DataNotFound;
import ru.jetlyn.zoo.services.DietService;

import javax.sql.DataSource;
import java.util.List;

/**
 * Сервис по работе с методами для сущностей Diet
 *
 * @getAllDiet - получить все рационы питания
 * @getDietBy - получить рационы питания по его Id
 * @getDietAnimalById - получить рационы питания животного
 * @getDietFoodById - получить животных питающихся продуктом
 * @saveDietAnimal - создать/сохранить рацион питания животного
 * @updateAnimal - обновить данные животного
 * @deleteAnimalById - удалить животное по его Id
 * @deleteAnimalByIds - удалить список животных по их Id
 * @deleteAllAnimal - удалить всех животных из хранилища
 */


@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;

    @Autowired
    public DietServiceImpl(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    @Override
    public List<Diet> getAllDiet() {
        return dietRepository.findAll();
    }

    @Override
    public Diet getDietBy(DietId dietId) {
        return dietRepository.findById(dietId)
                .orElseThrow(() -> new DataNotFound("Diet with id %d was not found in the database"));
    }

    @Override
    public List<Diet> getDietAnimalById(long animalId) {
        return dietRepository.findDietsByAnimal_Id(animalId);
    }

    @Override
    public List<Diet> getDietFoodById(long foodId) {
        return dietRepository.findDietByFood_Id(foodId);
    }

    @Override
    public Diet saveDietAnimal(Diet diet) {
        return dietRepository.save(diet);
    }

    @Override
    public Diet updateDietAnimal(Diet diet) {
        Diet upDiet = dietRepository.findById(diet.getDietId())
                .orElseThrow(() -> new DataNotFound("Diet with id %d was not found in the database"));

        upDiet.setAmount(diet.getAmount());

        return dietRepository.save(upDiet);
    }

    @Override
    public void deleteAnimal(long animalId) {

    }

    @Override
    public void deleteFood(long foodId) {

    }

    @Override
    public void deleteAll() {
        ResourceDatabasePopulator d = new ResourceDatabasePopulator();
        d.addScript(new ClassPathResource("scriptForDB.sql"));
    }
}
