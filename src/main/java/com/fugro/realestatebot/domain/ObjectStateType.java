package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum ObjectStateType {

    WITHOUT_REPAIRS("without_repairs","Без ремонта"),
    LIVING("living", "Жилое"),
    BLOCKS("blocks", "С ремонтом"),
    WITH_REPAIR("with_repair", "Монолит"),
    LUXURY("luxury", "Элитное");

    private final String codeName;
    private final String name;

    ObjectStateType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObjectStateType getByCodeName(String codeName) {
        return Arrays.stream(ObjectStateType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
