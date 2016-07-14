package com.chortitzer.web.usi;

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
        entityManagerFactoryRef = "usiEntityManagerFactory",
        transactionManagerRef = "usiTransactionManager",
        basePackages = {"com.chortitzer.web.usi"})
public class RepositoryConfig_usi {

    @Bean
    @ConfigurationProperties(prefix = "usi.datasource")
    public DataSource dataSource_usi() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean usiEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource_usi())
                .packages("com.chortitzer.web.usi.domain")
                .persistenceUnit("PU_usi")
                .build();
    }

    @Bean
    PlatformTransactionManager usiTransactionManager(EntityManagerFactoryBuilder factory) {
        return new JpaTransactionManager(usiEntityManagerFactory(factory).getObject());
    }

}
