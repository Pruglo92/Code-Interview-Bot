package ru.study.codeinterviewbot.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.study.codeinterviewbot.enums.Languages;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "language")
public class Language extends BaseEntity {

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "command", unique = true, nullable = false)
    private Languages command;

    @OneToMany(mappedBy = "language")
    private List<JavaSection> javaSection;
}
