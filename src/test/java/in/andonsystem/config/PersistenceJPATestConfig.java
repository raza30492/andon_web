/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.config;

import java.util.Properties;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Md Zahid Raza
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"in.andonsystem.repository"})
@PropertySource(value = "classpath:datasource.properties")
public class PersistenceJPATestConfig{
 
   private Logger logger = Logger.getLogger("PersistenceJPAConfig");
   
   @Autowired
   private Environment env;
   
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      logger.info("Inside entityManagerFactory() method");
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "in.andonsystem.entity" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
   @Bean
   public DataSource dataSource(){
      logger.info("Inside dataSource() method");
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
      dataSource.setUrl("jdbc:mysql://localhost:3306/test");
      dataSource.setUsername( env.getRequiredProperty("jdbc.username") );
      dataSource.setPassword( env.getRequiredProperty("jdbc.password") );
      return dataSource;
   }
 
   @Bean
   @Autowired
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      logger.info("Inside transactionManager() method");
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
 
      return transactionManager;
   }
 /*
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      logger.info("Inside exceptionTranslation() method");
      return new PersistenceExceptionTranslationPostProcessor();
   }
 */
   Properties additionalProperties() {
      logger.info("Inside additionalProperties() method");
      Properties properties = new Properties();
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      properties.setProperty("hibernate.show_sql", "false");
      properties.setProperty("hibernate.format_sql", "true");
      return properties;
   }
   
    //@Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
