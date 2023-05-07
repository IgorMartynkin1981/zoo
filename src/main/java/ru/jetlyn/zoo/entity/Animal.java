package ru.jetlyn.zoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import ru.jetlyn.zoo.entity.enums.Species;

import javax.persistence.*;
import java.util.List;

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

    @Transient
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Diet> dietList;
}
