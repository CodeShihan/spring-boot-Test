package com.spring.ws.spring.sql;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbProperty implements Serializable {

    /**
     * 连接池
     */
    private final String driverClassName="com.mysql.cj.jdbc.Driver";

    /**
     * 数据源
     */
	private String url;

    /**
     * 数据库
     */
    private String dbName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 表名
     */
    private String tableName;

}
