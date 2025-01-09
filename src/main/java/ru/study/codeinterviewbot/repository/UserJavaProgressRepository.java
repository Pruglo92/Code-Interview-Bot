package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.codeinterviewbot.entity.UserJavaProgress;

import java.util.List;

@Repository
public interface UserJavaProgressRepository extends JpaRepository<UserJavaProgress, Long> {

    List<UserJavaProgress> findAllByJavaSectionId(Long sectionId);

    void deleteAllByUserIdAndJavaSectionId(Long userId, Long sectionId);
}