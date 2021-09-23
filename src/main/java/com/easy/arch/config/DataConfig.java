package com.easy.arch.config;

import com.easy.arch.config.info.JdbcInfo;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataConfig {
    @Resource
    private JdbcInfo jdbcInfo;

    @Bean
    public DataSource getDataSource() throws SQLException{
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setDriverClassName(jdbcInfo.getDerive());
        dataSource.setJdbcUrl(jdbcInfo.getUrl());
        dataSource.setUsername(jdbcInfo.getUname());
        dataSource.setPassword(jdbcInfo.getUname());
        System.out.println("get data source");

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
//        System.out.println("getJdbcTemplate");
        return new JdbcTemplate(dataSource);
    }


//     * 装配事务管理器

    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


     //* JDBC事务操作配置

    @Bean(name = "txTemplate")
    public TransactionTemplate transactionTemplate (DataSourceTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
}
