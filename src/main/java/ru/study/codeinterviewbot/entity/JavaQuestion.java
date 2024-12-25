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
@Table(name = "java_question")
public class JavaQuestion extends BaseEntity {

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "java_section_id")
    private JavaSection javaSection;

    @OneToMany(mappedBy = "javaQuestion")
    private List<JavaAnswer> javaAnswer;

    @OneToMany(mappedBy = "javaQuestion")
    private List<UserJavaProgress> userJavaProgress;
}
