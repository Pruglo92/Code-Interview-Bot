package ru.study.codeinterviewbot.enums;

import lombok.Getter;

@Getter
public enum Languages {
    JAVA("/java");

    private final String description;

    Languages(String description) {
        this.description = description;
    }

}