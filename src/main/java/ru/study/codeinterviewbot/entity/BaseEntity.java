package ru.study.codeinterviewbot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    /**
     * Идентификатор сущности.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}
