package com.chortitzer.web.bas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "basEntityManagerFactory",
        transactionManagerRef = "basTransactionManager",
        basePackages = {"com.chortitzer.web.bas"})
public class RepositoryConfig_bas {

    @Bean
    @ConfigurationProperties(prefix = "bas.datasource")
    public DataSource dataSource_bas() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean basEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource_bas())
                .packages("com.chortitzer.web.bas.domain")
                .persistenceUnit("PU_bas")
                .build();
    }

    @Bean
    PlatformTransactionManager basTransactionManager(EntityManagerFactoryBuilder factory) {
        return new JpaTransactionManager(basEntityManagerFactory(factory).getObject());
    }

}
