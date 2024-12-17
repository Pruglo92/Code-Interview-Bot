package ru.study.codeinterviewbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "java_answers")
public class JavaAnswer extends BaseEntity{

    @Column
    private String text;

    @Column
    private Boolean isCorrect;;

    @ManyToOne
    @JoinColumn(name = "java_question_id")
    private JavaQuestion javaQuestion;

}
