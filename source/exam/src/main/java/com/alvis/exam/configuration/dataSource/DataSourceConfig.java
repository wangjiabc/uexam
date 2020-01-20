package com.alvis.exam.configuration.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment env;


    /**
     * sqlserver数据源
     *
     * @return
     */
    @Bean("sqlserver") // bean的名称
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver.hikari") // application.properteis中对应属性的前缀
    public DataSource sqlserverDataSource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * mysql数据源
     *
     * @return
     */
    @Bean("mysql") // bean的名称
    @ConfigurationProperties(prefix = "spring.datasource.mysql.hikari") // application.properteis中对应属性的前缀
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 动态数据源配置
     *
     * @return
     */
    @Primary
    @Bean("dataSource")
    public DynamicDataSource dataSource(@Qualifier("mysql") DataSource mysql,
                                        @Qualifier("sqlserver") DataSource sqlserver) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceDialect.MYSQL, mysql);
        targetDataSources.put(DataSourceDialect.SQLSERVER, sqlserver);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 设置默认的数据源
        dynamicDataSource.setDefaultTargetDataSource(mysql);

        return dynamicDataSource;
    }

    @Primary
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql") DataSource mysql, @Qualifier("sqlserver") DataSource sqlserver) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(this.dataSource(mysql, sqlserver));
        sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));

        return sessionFactory.getObject();
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
