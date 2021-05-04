package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum AptType {

    APT("apt", "Вторичный"),
    ROOM("room", "Комната"),
    NEW_BUILDING("new_building", "Новострой");

    private final String codeName;
    private final String name;

    AptType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AptType getByCodeName(String codeName) {
        return Arrays.stream(AptType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
