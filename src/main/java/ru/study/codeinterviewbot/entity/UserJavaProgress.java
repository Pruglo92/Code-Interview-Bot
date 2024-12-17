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
@Table(name = "users_ java_progress")
public class UserJavaProgress extends BaseEntity {

    @Column
    private Boolean is_correct;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "java_section_id")
    private JavaSection javaSection;

    @ManyToOne
    @JoinColumn(name = "java_question_id")
    private JavaQuestion javaQuestion;
}
