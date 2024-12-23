package ru.study.codeinterviewbot.enums;

import lombok.Getter;

@Getter
public enum Languages {
    JAVA("/java"),
    PYTHON("/python"),
    JAVASCRIPT("/javascript"),
    GO("/go"),
    TYPESCRIPT("/typescript"),
    PHP("/php");

    private final String value;

    Languages(String value) {
        this.value = value;
    }

}