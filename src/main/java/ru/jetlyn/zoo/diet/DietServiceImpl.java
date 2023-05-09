package ru.jetlyn.zoo.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.animal.Animal;
import ru.jetlyn.zoo.animal.dto.AnimalFoodNorm;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.animal.dto.AnimalMapper;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;
import ru.jetlyn.zoo.exception.DataNotFound;
import ru.jetlyn.zoo.food.Food;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Collection<Diet> getAll() {
        return dietRepository.findAll();
    }

    @Override
    public Collection<AnimalInfo> getAllDietsAnimal(Species species, String predator,
                                                    TypeOfProduct typeOfProduct, String name,
                                                    Integer from, Integer size) {

        int page = from / size;
        PageRequest pageRequest = PageRequest.of(page, size);

        if (species != null) return getDietsAnimal(dietRepository.findDietByAnimal_Species(species, pageRequest));
        if (predator != null) {
            if (predator.equals("true"))
                return getDietsAnimal(dietRepository.findDietByAnimal_Predator(true, pageRequest));
            if (predator.equals("false"))
                return getDietsAnimal(dietRepository.findDietByAnimal_Predator(false, pageRequest));
        }
        if (typeOfProduct != null)
            return getDietsAnimal(dietRepository.findDietByFood_TypeOfProduct(typeOfProduct, pageRequest));
        if (name != null) return getDietsAnimal(dietRepository.findDietByAnimal_Like(name));

        return getDietsAnimal(dietRepository.findAll(pageRequest).toList());
    }

    @Override
    public Diet getDietById(DietId dietId) {
        return dietRepository.findById(dietId)
                .orElseThrow(() -> new DataNotFound("Diet with id %d was not found in the database"));
    }

   @Override
    public Diet saveDietAnimal(Diet diet) {
        return dietRepository.save(diet);
    }

    @Override
    public Collection<AnimalInfo> saveDietsAnimal(Animal animal, List<Food> foodList, double amount) {
        return null;
    }

    @Override
    public Diet updateDietAnimal(Diet diet) {
        Diet upDiet = dietRepository.findById(diet.getDietId())
                .orElseThrow(() -> new DataNotFound("Diet with id %d was not found in the database"));

        upDiet.setAmount(diet.getAmount());

        return dietRepository.save(upDiet);
    }

    public Collection<AnimalInfo> getDietsAnimal(Collection<Diet> dietList) {
        Set<AnimalInfo> animalInfoList = new HashSet<>();
        for (Diet diet : dietList) {
            AnimalInfo animalInfo = new AnimalInfo(diet.getAnimal().getName(), diet.getAnimal().getSpecies());
            if (!animalInfoList.contains(animalInfo)) {
                List<AnimalFoodNorm> animalFoodNormList = dietList.stream()
                        .filter(x -> x.getAnimal().getName().equals(animalInfo.getName()))
                        .map(AnimalMapper::toAnimalFoodNorm).collect(Collectors.toList());
                animalInfo.setDietList(animalFoodNormList);
                animalInfoList.add(animalInfo);
            }
        }
        return animalInfoList;
    }
}
