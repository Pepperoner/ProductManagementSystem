package com.management.product.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.management.product.repository")
@ComponentScan(basePackages = "com.management.product.entity")
@PropertySource(value = "classpath:database.properties")
public class RootConfig  {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.driver.url}")
    private String jdbcDriverUrl;

    @Value("${database.host.ip}")
    private String hostIp;

    @Value("${database.host.port}")
    private String hostPort;

    @Value("${database.name}")
    private String databaseName;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.use-ssl}")
    private String useSsl;

    @Value("${database.use-unicode}")
    private String useUnicode;

    @Value("${database.character-encoding}")
    private String characterEncoding;

    @Value("${database.use-jdbc-compliant-timezone-shift}")
    private boolean useJdbcCompliantTimezoneShift;

    @Value("${database.use-legacy-datetime-code}")
    private boolean useLegacyDatetimeCode;

    @Value("${database.server-timezone}")
    private String serverTimezone;

    @Value("${database.initial-size}")
    private int initialSize;

    @Value("${database.max-active}")
    private int maxActive;

    @Value("${database.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${database.validation-query}")
    private String validationQuery;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show-sql}")
    private boolean isShowSql;

    @Value("${hibernate.generate-ddl}")
    private boolean isGenerateDdl;

    @Value("${hibernate.entity-packages}")
    private String entityPackages;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.jdbcDriver);
        dataSource.setUrl(getDataSourceUrl());
        dataSource.setConnectionProperties(createDatabaseConnectionProperties());
        dataSource.setUsername(this.databaseUsername);
        dataSource.setPassword(this.databasePassword);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setValidationQuery(this.validationQuery);
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(this.isShowSql);
        hibernateJpaVendorAdapter.setGenerateDdl(this.isGenerateDdl);
        hibernateJpaVendorAdapter.setDatabasePlatform(this.hibernateDialect);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            HibernateJpaVendorAdapter hibernateJpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        emf.setPackagesToScan(this.entityPackages);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private String getDataSourceUrl() {
        return this.jdbcDriverUrl + "://" + this.hostIp + ":" + this.hostPort + "/" + this.databaseName;
    }

    private String createDatabaseConnectionProperties() {
        return "useSSL=" + this.useSsl + ";"
                + "useUnicode=" + this.useUnicode + ";"
                + "characterEncoding=" + this.characterEncoding + ";"
                + "useJDBCCompliantTimezoneShift=" + this.useJdbcCompliantTimezoneShift + ";"
                + "useLegacyDatetimeCode=" + this.useLegacyDatetimeCode + ";"
                + "serverTimezone=" + this.serverTimezone;
    }
}
