package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum MaterialType {

    BRICK("brick","Кирпич"),
    PANELS("panels", "Панели"),
    BLOCKS("blocks", "Блоки"),
    MONOLITH("monolit", "Монолит"),
    FOAM_CONCRETE("foamconcrete", "Пенобетон"),
    AERATED_CONCRETE("aeratedconcrete", "Газобетон"),
    SLAG("slag", "Шлак"),
    SHELL_ROCK("shellrock", "Ракушняк"),
    WOODS("woods", "Сруб"),
    METAL("metall", "Метал");

    private final String codeName;
    private final String name;

    MaterialType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MaterialType getByCodeName(String codeName) {
        return Arrays.stream(MaterialType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
