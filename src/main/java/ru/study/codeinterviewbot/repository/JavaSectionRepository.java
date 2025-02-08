package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.codeinterviewbot.entity.JavaSection;

@Repository
public interface JavaSectionRepository extends JpaRepository<JavaSection, Long> {
}
