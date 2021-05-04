package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum SourceType {

    RIELTOR("rieltor", "Риэлтор"),
    OWNER("owner", "Собственник");

    private final String codeName;
    private final String name;

    SourceType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SourceType getByCodeName(String codeName) {
        return Arrays.stream(SourceType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
