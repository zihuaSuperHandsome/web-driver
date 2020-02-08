package com.zihua.webspider.enums;

public enum MessageEnum {

    preview("預覽圖"),
    magnet("磁力鏈接 ( 如何下載观看影片? )"),
    action("TA(們)還出演過"),
    recom("你可能也喜歡");

    private String bar;

    MessageEnum(String bar) {
        this.bar = bar;
    }
    public String getBar() {
        return this.bar;
    }
}
