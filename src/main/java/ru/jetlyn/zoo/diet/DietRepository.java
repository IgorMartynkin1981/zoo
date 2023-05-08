package ru.jetlyn.zoo.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, DietId> {

    List<Diet> findDietsByAnimal_Id(long animalId);

    List<Diet> findDietByFood_Id(long foodId);

}
