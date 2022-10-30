package com.spring.ws.spring.database.inner;

import com.spring.ws.spring.database.fina.DataBaseConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

public interface DataSourceService {
    DataSource DataBaseSource();

    SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception;

    SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory);

    DataSourceTransactionManager getTransactionManager(DataSource dataSource);

    JdbcTemplate getJdbcTemplate(DataSource dataSource);
}
