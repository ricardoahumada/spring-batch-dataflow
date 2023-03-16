package es.netmind.banana_invoices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class PersistenceConfig {
    @Autowired
    private Environment env;

    @Bean // JPA transaction manager
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(invoicesEmf().getObject());
        return transactionManager;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    DataSource invoicesDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(env.getProperty("invoices.url"));

        ds.setDriverClassName(env.getProperty("invoices.driverClassName"));
        ds.setUsername(env.getProperty("invoices.dbUserName"));
        ds.setPassword(env.getProperty("invoices.password"));

        return ds;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(true);
        return va;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean invoicesEmf() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPersistenceUnitName(env.getProperty("invoices.persistenceUnitName"));

        em.setDataSource(invoicesDataSource());
        em.setPackagesToScan("es.netmind.banana_invoices.*");
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("invoices.dialect"));
        return properties;
    }

}
