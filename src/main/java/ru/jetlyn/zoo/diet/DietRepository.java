package ru.jetlyn.zoo.diet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.jetlyn.zoo.enums.Species;
import ru.jetlyn.zoo.enums.TypeOfProduct;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, DietId> {

    List<Diet> findDietByAnimal_Species(Species species, Pageable pageRequest);

    List<Diet> findDietByAnimal_Predator(boolean predator, Pageable pageRequest);

    List<Diet> findDietByFood_TypeOfProduct(TypeOfProduct typeOfProduct, Pageable pageRequest);

    @Modifying
    @Query(value = "SELECT b FROM Diet AS b WHERE b.animal.name LIKE ?1%")
    List<Diet> findDietByAnimal_Like(String name);

    Diet findDietByAnimal_IdAndFood_Id(long animalId, long foodId);
}
