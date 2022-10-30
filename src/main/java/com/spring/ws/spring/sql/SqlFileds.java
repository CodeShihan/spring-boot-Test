package com.spring.ws.spring.sql;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SqlFileds {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 主键
     */
    private String columnKey;

    /**
     * 字段备注
     */
    private String columnComment;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 是否为空
     */
    private String isNullable;

}
