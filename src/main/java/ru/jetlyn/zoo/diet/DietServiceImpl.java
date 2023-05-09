package ru.jetlyn.zoo.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.jetlyn.zoo.animal.AnimalService;
import ru.jetlyn.zoo.animal.dto.AnimalFoodNorm;
import ru.jetlyn.zoo.animal.dto.AnimalInfo;
import ru.jetlyn.zoo.animal.dto.AnimalMapper;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;
import ru.jetlyn.zoo.exception.ValidationDataException;
import ru.jetlyn.zoo.food.Food;
import ru.jetlyn.zoo.food.FoodService;
import ru.jetlyn.zoo.food.dto.FoodDietInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис по работе с методами для сущностей Diet
 *
 * @getAll() - получить меню животных.
 * @getAllDietsAnimal - Получить информацию о животных, с перечислением продуктов их питания.
 * @getDietById - получить ежедневную норму для животного по его id.
 * @saveDietAnimal - Назначение продукта животному.
 * @updateDietAnimal - Изменение ежедневной нормы для животного по его id.
 * @informationDiet - Получить информацию о питании животных на 7 дней от заданной даты.
 */


@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;
    private final AnimalService animalService;
    private final FoodService foodService;

    @Autowired
    public DietServiceImpl(DietRepository dietRepository, AnimalService animalService, FoodService foodService) {
        this.dietRepository = dietRepository;
        this.animalService = animalService;
        this.foodService = foodService;
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
    public Diet getDietById(long animalId, long foodId) {
        return dietRepository.findDietByAnimal_IdAndFood_Id(animalId, foodId);
    }

    @Override
    public Diet saveDietAnimal(long animalId, long foodId, long amount) {
        Diet diet = new Diet();
        diet.setDietId(new DietId(animalId, foodId));
        diet.setAnimal(animalService.getAnimal(animalId));
        diet.setFood(foodService.getFood(foodId));
        diet.setAmount(amount);

        return dietRepository.save(diet);
    }

    @Override
    public Diet updateDietAnimal(long animalId, long foodId, long amount) {
        Diet diet = getDietById(animalId, foodId);

        diet.setAmount(amount);

        return dietRepository.save(diet);
    }

    /**
     * @param periodStartString Если заданная дана больше текущей, то возвращаем
     *                          необходимое количество продуктов на 7 дней,
     *                          а нехватку продуктов высчитывае как:
     *                          остаток продуктов на складе - необходимое количество продуктов на 7 дней
     *                          и - необходимое количество продуктов на разницу дней текущей даты и заданной
     * @return List<FoodDietInfo>
     */
    @Override
    public List<FoodDietInfo> informationDiet(String periodStartString) {
        long dietForDays = 7;
        LocalDate periodStart = LocalDate.now();

        if (periodStartString != null)
            periodStart = LocalDate.parse(periodStartString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (periodStart.isBefore(LocalDate.now()))
            throw new ValidationDataException("Заданная дата не может быть меньше текущей");

        dietForDays += Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), periodStart));

        Map<Food, Double> mapDietFoodForDays = new HashMap<>();
        List<Diet> dietList = dietRepository.findAll();

        for (Diet diet : dietList) {
            if (mapDietFoodForDays.containsKey(diet.getFood())) {
                mapDietFoodForDays.put(diet.getFood(),
                        mapDietFoodForDays.get(diet.getFood()) + diet.getAmount());
            } else {
                mapDietFoodForDays.put(diet.getFood(), diet.getAmount());
            }
        }

        List<FoodDietInfo> foodDietInfos = new ArrayList<>();

        for (Map.Entry<Food, Double> entry : mapDietFoodForDays.entrySet()) {
            FoodDietInfo foodDietInfo = new FoodDietInfo((entry.getKey().getName()),
                    (entry.getValue() * 7),
                    (entry.getKey().getAmound()));
            if ((entry.getValue() * dietForDays) > entry.getKey().getAmound()) {
                foodDietInfo.setDeficitFood((entry.getValue() * dietForDays) - entry.getKey().getAmound());
            }
            foodDietInfos.add(foodDietInfo);
        }

        return foodDietInfos;
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
