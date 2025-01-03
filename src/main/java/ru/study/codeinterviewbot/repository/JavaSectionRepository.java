package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.study.codeinterviewbot.entity.JavaSection;

public interface JavaSectionRepository extends JpaRepository<JavaSection, Long> {
}
