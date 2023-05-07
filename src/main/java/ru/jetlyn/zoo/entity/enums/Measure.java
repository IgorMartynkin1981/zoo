package ru.jetlyn.zoo.entity.enums;

/**
 * Measure Единица измерения(кг/шт/л)
 * используется в сущности Food
 */

public enum Measure {
    kg("кг", "килограмм"),
    pcs("шт", "штука"),
    l("л", "литр");

    private final String abbreviationInRus;
    private final String translateIntoRus;

    Measure(String abbreviationInRus, String translateIntoRus) {
        this.abbreviationInRus = abbreviationInRus;
        this.translateIntoRus = translateIntoRus;
    }
}
