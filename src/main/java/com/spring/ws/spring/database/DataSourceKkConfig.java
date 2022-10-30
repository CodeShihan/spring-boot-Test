package com.spring.ws.spring.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.ws.spring.database.fina.DataBaseConfig;
import com.spring.ws.spring.database.inner.DataSourceService;
import com.spring.ws.spring.database.properties.DataBaseKkProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages=DataSourceKkConfig.KK_PACKAGE,sqlSessionFactoryRef = DataBaseConfig.KK_Sql_Session_Factory)
public class DataSourceKkConfig implements DataSourceService {

    public static final String KK_PACKAGE="com.spring.ws.spring.dao.kk";

    public static final String KK_MAPPER="classpath*:mapper/xr/*.xml";

    @Autowired
    private DataBaseKkProperties dataBaseKkProperties;

    @Override
    @Bean(name= DataBaseConfig.Kk_DataSource)
    public DataSource DataBaseSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataBaseKkProperties.getUrl());
        dataSource.setUsername(dataBaseKkProperties.getUsername());
        dataSource.setPassword(dataBaseKkProperties.getPassword());
        dataSource.setDriverClassName(dataBaseKkProperties.getDriverClassName());
        return dataSource;
    }


    @Override
    @Bean(name= DataBaseConfig.KK_Sql_Session_Factory)
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DataBaseConfig.Kk_DataSource)DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(DataSourceKkConfig.KK_MAPPER);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Override
    @Bean(name= DataBaseConfig.KK_Sql_Session_Template)
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier(DataBaseConfig.KK_Sql_Session_Factory) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    @Bean(name= DataBaseConfig.KK_TRANSACTION_MANAGER)
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DataBaseConfig.Kk_DataSource)DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    @Bean(name = DataBaseConfig.KK_JDBCTEMPLATE)
    public JdbcTemplate getJdbcTemplate(@Qualifier(DataBaseConfig.Kk_DataSource)DataSource dataSource) {
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }


}
