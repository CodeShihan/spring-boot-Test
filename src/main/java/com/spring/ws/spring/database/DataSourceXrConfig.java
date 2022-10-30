package com.spring.ws.spring.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.ws.spring.database.fina.DataBaseConfig;
import com.spring.ws.spring.database.inner.DataSourceService;
import com.spring.ws.spring.database.properties.DataBaseXrProperties;
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
@MapperScan(basePackages = DataSourceXrConfig.XR_PACKAGE,sqlSessionFactoryRef = DataBaseConfig.XR_Sql_Session_Factory)
public class DataSourceXrConfig implements DataSourceService {

    public static final String XR_PACKAGE="com.spring.ws.spring.dao.xr";

    public static final String XR_MAPPER="classpath*:mapper/xr/*.xml";

    @Autowired
    private DataBaseXrProperties dataBaseXrProperties;

    //--------------------------锐-------------------------------
    @Bean(name= DataBaseConfig.Xr_DataSource)
    public DataSource DataBaseSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(dataBaseXrProperties.getUrl());
        dataSource.setUsername(dataBaseXrProperties.getUsername());
        dataSource.setPassword(dataBaseXrProperties.getPassword());
        dataSource.setDriverClassName(dataBaseXrProperties.getDriverClassName());
        return dataSource;
    }

    @Bean(name= DataBaseConfig.XR_Sql_Session_Factory)
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DataBaseConfig.Xr_DataSource) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(DataSourceXrConfig.XR_MAPPER);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean(name= DataBaseConfig.XR_SQL_Session_Template)
    @Primary
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier(DataBaseConfig.XR_Sql_Session_Factory) SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name= DataBaseConfig.XR_TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DataBaseConfig.Xr_DataSource)DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = DataBaseConfig.XR_JDBCTEMPLATE)
    public JdbcTemplate getJdbcTemplate(@Qualifier(DataBaseConfig.Xr_DataSource)DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

}
