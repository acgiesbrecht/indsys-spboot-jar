package com.chortitzer.web.admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "adminEntityManagerFactory",
        transactionManagerRef = "adminTransactionManager",
        basePackages = {"com.chortitzer.web.admin"})
public class RepositoryConfig_admin {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "admin.datasource")
    public DataSource dataSource_admin() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource_admin())
                .packages("com.chortitzer.web.admin.domain")
                .persistenceUnit("PU_admin")
                .build();
    }

    @Bean
    @Primary
    PlatformTransactionManager adminTransactionManager(EntityManagerFactoryBuilder factory) {
        return new JpaTransactionManager(adminEntityManagerFactory(factory).getObject());
    }

}
