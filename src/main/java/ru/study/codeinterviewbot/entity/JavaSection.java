package ru.study.codeinterviewbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "java_section")
public class JavaSection extends BaseEntity {

    @Column
    private String name;

    @Column
    public String command;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @OneToMany(mappedBy = "javaSection")
    private List<JavaQuestion> javaQuestion;

    @OneToMany(mappedBy = "javaSection")
    private List<UserJavaProgress> userJavaProgress;

}
