package ru.jetlyn.zoo.entity;

/**
 * Species Вид (млекопитающее/птица)
 * используется в сущности Animal
 */
public enum Species {
    MAMMAL("млекопитающее"),
    BIRD("птица");

    private final String translateIntoRus;

    Species(String translateIntoRus) {
        this.translateIntoRus = translateIntoRus;
    }
}
