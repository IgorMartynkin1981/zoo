package ru.jetlyn.zoo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность Животное
 * Состоит из полей:
 * @id
 * @name Название,
 * @species Вид (млекопитающее/птица)
 * @predator Признак хищника (да/нет). По умолчанию НЕТ
 */

@Data
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Species species;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean predator;
}
