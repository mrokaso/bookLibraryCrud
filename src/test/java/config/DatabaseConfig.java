package config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DatabaseConfig {
    private String host = "mrokas.ayz.pl";
    private String port = "3306";
    private String databaseName = "mrokas_crud";
    private String username = "mrokas_main";
    private String password = "itmeetinzynierka";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean localFactory =
                new LocalContainerEntityManagerFactoryBean();
        localFactory.setDataSource(dataSource());
        localFactory.setPackagesToScan("data/entities");
        localFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localFactory.setJpaProperties(property());

        return localFactory;
    }

    @Bean
    public Properties property() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");

        return properties;
    }

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8");

        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exception(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}