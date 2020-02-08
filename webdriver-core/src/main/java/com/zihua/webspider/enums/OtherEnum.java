package com.zihua.webspider.enums;

import lombok.Getter;

public enum OtherEnum {

    PRIMARY_KEY("NOT NULL AUTO_INCREMENT"),
    NOT_PRIMARY_KEY("DEFAULT NULL");

    OtherEnum(String value) {
        this.value = value;
    }

    @Getter
    private String value;
}
