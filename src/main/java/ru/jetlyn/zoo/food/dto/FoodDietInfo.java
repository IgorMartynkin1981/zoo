package ru.jetlyn.zoo.food.dto;

import lombok.Data;

@Data
public class FoodDietInfo {
    private String nameFood;
    private double needFood;
    private double balanceFood;
    private double deficitFood;

    public FoodDietInfo(String nameFood, double needFood, double balanceFood) {
        this.nameFood = nameFood;
        this.needFood = needFood;
        this.balanceFood = balanceFood;
    }

    @Override
    public String toString() {
        return "FoodDietInfo{" +
                "Продукт ='" + nameFood + '\'' +
                ", требуется на 7 дней =" + needFood +
                ", на складе =" + balanceFood +
                ", дефицит =" + deficitFood +
                '}';
    }
}
