package com.chortitzer.web.fps;

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
        entityManagerFactoryRef = "fpsEntityManagerFactory",
        transactionManagerRef = "fpsTransactionManager",
        basePackages = {"com.chortitzer.web.fps"})
public class RepositoryConfig_fps {

    @Bean
    @ConfigurationProperties(prefix = "fps.datasource")
    public DataSource dataSource_fps() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean fpsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource_fps())
                .packages("com.chortitzer.web.fps.domain")
                .persistenceUnit("PU_fps")
                .build();
    }

    @Bean
    PlatformTransactionManager fpsTransactionManager(EntityManagerFactoryBuilder factory) {
        return new JpaTransactionManager(fpsEntityManagerFactory(factory).getObject());
    }

}
