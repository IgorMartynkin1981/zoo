package ru.jetlyn.zoo.data;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jetlyn.zoo.entity.Diet;
import ru.jetlyn.zoo.entity.DietId;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, DietId> {

    List<Diet> findDietsByAnimal_Id(long animalId);

    List<Diet> findDietByFood_Id(long foodId);

}
