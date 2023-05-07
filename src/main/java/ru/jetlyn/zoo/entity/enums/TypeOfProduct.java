package ru.jetlyn.zoo.entity.enums;

/**
 * Type Тип (овощ/фрукт/мясо/жидкость)
 * используется в сущности Food
 */

public enum TypeOfProduct {
    VEGETABLE("овощ"),
    FRUIT("фрукт"),
    MEAT("мясо"),
    LIQUID("жидкость");

    private final String translateIntoRus;

    TypeOfProduct(String translateIntoRus) {
        this.translateIntoRus = translateIntoRus;
    }

}
