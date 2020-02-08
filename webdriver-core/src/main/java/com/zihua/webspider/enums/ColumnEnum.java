package com.zihua.webspider.enums;

public enum ColumnEnum {

    number("番號"),
    releas_time("時間"),
    length("時長"),
    dictor("導演"),
    distributor("片商"),
    publisher("發行"),
    type("类别"),
    actor("演員"),
    series("系列");

    private String key;

    ColumnEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}