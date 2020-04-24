package com.example.esclient.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created By admin on 2020.01.24
 */
@Configuration
public class DateSourceConfig {

//    @Bean(name = "oracleDataSource")
//
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource oracleDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Primary
    @Bean("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate oracleNamedParameterJdbcTemplate(
            @Qualifier("druidDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
