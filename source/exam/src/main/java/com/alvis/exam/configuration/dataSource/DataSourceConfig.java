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
    @Bean("mysql2") // bean的名称
    @ConfigurationProperties(prefix = "spring.datasource.mysql2") // application.properteis中对应属性的前缀
    public DataSource sqlserverDataSource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * mysql数据源
     *
     * @return
     */
    @Bean("mysql1") // bean的名称
    @ConfigurationProperties(prefix = "spring.datasource.mysql1") // application.properteis中对应属性的前缀
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
    public DynamicDataSource dataSource(@Qualifier("mysql1") DataSource mysql,
                                        @Qualifier("mysql2") DataSource mysql2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceDialect.MYSQL1, mysql);
        targetDataSources.put(DataSourceDialect.MYSQL2, mysql2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 设置默认的数据源
        dynamicDataSource.setDefaultTargetDataSource(mysql);
        return dynamicDataSource;
    }

    @Primary
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql1") DataSource mysql1, @Qualifier("mysql2") DataSource mysql2,org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(this.dataSource(mysql1, mysql2));
        sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }
}
