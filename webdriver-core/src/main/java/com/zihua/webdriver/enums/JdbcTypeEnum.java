package com.zihua.webdriver.enums;


import org.apache.ibatis.type.TypeHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘子华
 */

public enum JdbcTypeEnum {
    BOOLEAN(JdbcType.BIT),
    BYTE(JdbcType.TINYINT),
    SHORT(JdbcType.SMALLINT),
    INTEGER(JdbcType.INTEGER),
    LONG(JdbcType.BIGINT),
    FLOAT(JdbcType.FLOAT),
    DOUBLE(JdbcType.DOUBLE),
    BIGDECIMAL(JdbcType.DECIMAL),
    STRING(JdbcType.VARCHAR),
    DATE(JdbcType.DATE),
    TIME(JdbcType.TIME),
    BigInteger(JdbcType.DECIMAL),
    Character(JdbcType.VARCHAR),
    BLOB(JdbcType.BLOB),
    UNKNOW(null);

    private static final Map<Class<?>, JdbcType> SIMPLE_TYPE_MAP = new HashMap<Class<?>, JdbcType>();

    private TypeHandler<?> typeHandler;
    private JdbcType jdbcType;

    private JdbcTypeEnum(JdbcType jdbcType) {
        this.typeHandler = typeHandler;
        this.jdbcType = jdbcType;
    }

    public TypeHandler<?> getTypeHandler() {
        return this.typeHandler;
    }

    public JdbcType getJdbcType() {
        return this.jdbcType;
    }

    public String getJdbcTypeFormat() {
        return this.jdbcType.TYPE_FORMAT_NAME;
    }

    public JdbcTypeEnum get(Class<?> clazz) {
        this.jdbcType = SIMPLE_TYPE_MAP.get(clazz);
        return this;
    }

    static {
        SIMPLE_TYPE_MAP.put(Integer.class, JdbcType.INTEGER);
        SIMPLE_TYPE_MAP.put(String.class, JdbcType.VARCHAR);
        SIMPLE_TYPE_MAP.put(Byte.class, JdbcType.TINYINT);
        SIMPLE_TYPE_MAP.put(Short.class, JdbcType.SMALLINT);
        SIMPLE_TYPE_MAP.put(Character.class, JdbcType.VARCHAR);
        SIMPLE_TYPE_MAP.put(Long.class, JdbcType.BIGINT);
        SIMPLE_TYPE_MAP.put(Float.class, JdbcType.FLOAT);
        SIMPLE_TYPE_MAP.put(Double.class, JdbcType.DOUBLE);
        SIMPLE_TYPE_MAP.put(Boolean.class, JdbcType.BIT);
        SIMPLE_TYPE_MAP.put(Date.class, JdbcType.DATE);
        SIMPLE_TYPE_MAP.put(BigInteger.class, JdbcType.DECIMAL);
        SIMPLE_TYPE_MAP.put(BigDecimal.class, JdbcType.DECIMAL);
        SIMPLE_TYPE_MAP.put(Time.class, JdbcType.TIME);
    }
}
