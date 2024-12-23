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
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Languages command;

    @OneToMany(mappedBy = "language")
    private List<JavaSection> javaSection;

}
