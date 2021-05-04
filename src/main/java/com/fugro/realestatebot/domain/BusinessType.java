package com.fugro.realestatebot.domain;

import java.util.Arrays;

public enum BusinessType {

    SELL("sell", "Продам"),
    RENT("rent", "Сдам"),
    RENT_PER_DAY("rent_per_day", "Сдам посуточно");

    private final String codeName;
    private final String name;

    BusinessType(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BusinessType getByCodeName(String codeName) {
        return Arrays.stream(BusinessType.values())
                .filter(type -> type.codeName.equals(codeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown codeName=" + codeName));
    }
}
