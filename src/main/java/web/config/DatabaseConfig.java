package web.config;

import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
public class DatabaseConfig {

    @Value("${spring.database.host}")
    private String host;
    @Value("${spring.database.port}")
    private int port;
    @Value("${spring.database.name}")
    private String databaseName;
    @Value("${spring.database.user}")
    private String username;
    @Value("${spring.database.password}")
    private String password;

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