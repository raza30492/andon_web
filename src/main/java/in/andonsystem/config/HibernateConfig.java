/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.config;


import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Md Zahid Raza
 */
//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = "classpath:datasource.properties")
public class HibernateConfig {
   
   @Autowired
   private Environment env;
   
   @Bean
   public LocalSessionFactoryBean sessionFactory(){
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setPackagesToScan("in.andonsystem.entities");
      //sessionFactory.setHibernateProperties(hibernateProperties);
      return sessionFactory;
   }
   
   @Bean
   @Autowired
   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
      return txManager;
   }
   
   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
      dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
      dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
      dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
      return dataSource;
   }
}
