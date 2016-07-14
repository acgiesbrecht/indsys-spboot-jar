package com.chortitzer.web.lab;

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
        entityManagerFactoryRef = "labEntityManagerFactory",
        transactionManagerRef = "labTransactionManager",
        basePackages = {"com.chortitzer.web.lab"})
public class RepositoryConfig_lab {

    @Bean
    @ConfigurationProperties(prefix = "lab.datasource")
    public DataSource dataSource_lab() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean labEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource_lab())
                .packages("com.chortitzer.web.lab.domain")
                .persistenceUnit("PU_lab")
                .build();
    }

    @Bean
    PlatformTransactionManager labTransactionManager(EntityManagerFactoryBuilder factory) {
        return new JpaTransactionManager(labEntityManagerFactory(factory).getObject());
    }

}
