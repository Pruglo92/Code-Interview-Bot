package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.codeinterviewbot.entity.JavaAnswer;

@Repository
public interface JavaAnswerRepository extends JpaRepository<JavaAnswer, Long> {
}