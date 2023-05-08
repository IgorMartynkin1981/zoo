package ru.jetlyn.zoo.diet;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite Primary Keys in JPA
 */

@Embeddable
public class DietId implements Serializable {

    @JoinColumn(name = "animal_id")
    private long animalId;

    @JoinColumn(name = "food_id")
    private long foodId;

    public DietId() {
    }

    public DietId(long animalId, long foodId) {
        this.animalId = animalId;
        this.foodId = foodId;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodid) {
        this.foodId = foodid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietId dietId = (DietId) o;
        return animalId == dietId.animalId && foodId == dietId.foodId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, foodId);
    }
}
