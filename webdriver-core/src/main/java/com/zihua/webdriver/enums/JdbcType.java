package com.zihua.webdriver.enums;

/**
 * @ClassName JdbcType
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/1 17:59
 */
public enum JdbcType {
    BIT("BIT", 1),
    TINYINT("TINYINT",4),
    SMALLINT("SMALLINT",10),
    INTEGER("INT",10),
    BIGINT("VARCHAR",20),
    FLOAT("FLOAT",10),
    DOUBLE("DOUBLE", 10),
    DECIMAL("DECIMAL",10),
    VARCHAR("VARCHAR",300),
    DATE("DATE",0),
    TIME("TIME",0),
    BLOB("BLOB",0);

    public final int TYPE_DEFAULT_LENGTH;
    public final String TYPE_FORMAT_NAME;

    private JdbcType(String format, int defaultLength) {
        this.TYPE_DEFAULT_LENGTH = defaultLength;
        this.TYPE_FORMAT_NAME = format;
    }
}

