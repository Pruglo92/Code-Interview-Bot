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
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "chat_id", unique = true, nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    List<UserJavaProgress> userJavaProgress;
}
