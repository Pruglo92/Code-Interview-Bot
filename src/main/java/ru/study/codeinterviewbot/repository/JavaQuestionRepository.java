package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.study.codeinterviewbot.entity.JavaQuestion;

import java.util.List;

@Repository
public interface JavaQuestionRepository extends JpaRepository<JavaQuestion, Long> {

    @Query("""
                SELECT jq
                FROM JavaQuestion jq
                LEFT JOIN FETCH jq.javaAnswer
                WHERE jq.javaSection.id = :sectionId
                AND jq.id NOT IN (
                    SELECT ujp.javaQuestion.id
                    FROM UserJavaProgress ujp
                    JOIN ujp.user u
                    WHERE u.chatId = :chatId AND ujp.javaSection.id = :sectionId
                )
            """)
    List<JavaQuestion> findQuestionsBySectionAndUserProgress(@Param("sectionId") String sectionId,
                                                             @Param("chatId") Long chatId);
}