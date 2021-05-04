package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum ObjectType {

    APTS_S("apts_s","Вторичный"),
    HOUSES_P("houses_p", "Дома и дач"),
    COMMERCE("commerce", "Коммерческая недвижимость"),
    LANDS("lands", "Земельные участки"),
    GARAGE("garage", "Гаражи и паркинги");

    private final String codeName;
    private final String name;

    ObjectType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObjectType getByCodeName(String codeName) {
        return Arrays.stream(ObjectType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
