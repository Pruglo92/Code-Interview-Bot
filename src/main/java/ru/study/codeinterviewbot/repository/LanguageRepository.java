package ru.study.codeinterviewbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.codeinterviewbot.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
