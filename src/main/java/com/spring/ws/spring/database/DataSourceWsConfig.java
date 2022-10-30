package com.spring.ws.spring.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.ws.spring.database.fina.DataBaseConfig;
import com.spring.ws.spring.database.inner.DataSourceService;
import com.spring.ws.spring.database.properties.DataBaseWsProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = DataSourceWsConfig.WS_PACKAGE,sqlSessionFactoryRef = DataBaseConfig.WS_Sql_Session_Factory)
public class DataSourceWsConfig implements DataSourceService {

    public static final String WS_PACKAGE="com.spring.ws.spring.dao.ws";

    public static final String WS_MAPPER="classpath*:mapper/ws/*.xml";

    @Autowired
    private DataBaseWsProperties dataBaseWsProperties;
    //--------------------------帅-------------------------------
    @Bean(DataBaseConfig.Ws_DataSource)
    public DataSource DataBaseSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(dataBaseWsProperties.getUrl());
        dataSource.setUsername(dataBaseWsProperties.getUsername());
        dataSource.setPassword(dataBaseWsProperties.getPassword());
        dataSource.setDriverClassName(dataBaseWsProperties.getDriverClassName());
        return dataSource;
    }

    @Bean(DataBaseConfig.WS_Sql_Session_Factory)
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DataBaseConfig.Ws_DataSource) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(DataSourceWsConfig.WS_MAPPER);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean(DataBaseConfig.WS_Sql_Session_Template)
    @Primary
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier(DataBaseConfig.WS_Sql_Session_Factory) SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(DataBaseConfig.WS_TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DataBaseConfig.Ws_DataSource)DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = DataBaseConfig.WS_JDBCTEMPLATE)
    public JdbcTemplate getJdbcTemplate(@Qualifier(DataBaseConfig.Ws_DataSource)DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
