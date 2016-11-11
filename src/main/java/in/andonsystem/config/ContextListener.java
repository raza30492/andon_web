package in.andonsystem.config;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import in.andonsystem.dto.AppPreferences;
import in.andonsystem.dto.UserForm;
import in.andonsystem.entity.User;
import in.andonsystem.service.DepartmentService;
import in.andonsystem.service.DesignationService;
import in.andonsystem.service.SectionService;
import in.andonsystem.service.UserService;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextListener implements ServletContextListener{

   private Logger logger = Logger.getLogger("ContextListener");
   
   @Autowired
   private AppPreferences appPref;
   
   @Autowired
   private SectionService sectionService;
   
   @Autowired
   private DepartmentService departmentService;
   
   @Autowired
   private DesignationService desgnService;
   
   @Autowired
   private DataSource dataSource;
   
   @Autowired
   private UserService userService;
   
   @Override
   public void contextInitialized(ServletContextEvent sce) {
      logger.info("contextInitialized()");
      
      WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext())
              .getAutowireCapableBeanFactory()
              .autowireBean(this);
      //Initialize database
      //runSQLScript();
      
      //Add Admin [Designation id of admin is 39]
      List<User> users = userService.findAll();
      if(users.isEmpty()){
         UserForm user = new UserForm("55555","Md Zahid Raza","8987525008",39);
         userService.saveUser(user);
      }
      //Initialize Application Scope Bean
      appPref.initialize(sectionService.findAll(),departmentService.findAll(false),desgnService.findAll());
      
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      logger.info("contextDestroyed()");
      try{
         AbandonedConnectionCleanupThread.shutdown();
         logger.info("AbandonedConnectionCleanupThread stopped.");
      }catch(InterruptedException e){
         logger.severe("Failed to stop AbandonedConnectionCleanupThread.");
      }
      
      Enumeration<Driver> drivers = DriverManager.getDrivers();
      Driver driver = null;
      while(drivers.hasMoreElements()){
         driver = drivers.nextElement();
         try{
            DriverManager.deregisterDriver(driver);
            logger.info(driver + " deregistered.");
         }catch(SQLException e){
            logger.severe("Failed to deregidter Driver");
         }
      }
   }
   
   private void runSQLScript(){
      ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
      rdp.addScript(new ClassPathResource("mysql-scripts/lines.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/sections.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/departments.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/problems.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/designations.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/desgn_line.sql"));
      rdp.addScript(new ClassPathResource("mysql-scripts/desgn_problem.sql"));
      
      try{
         Connection conn = dataSource.getConnection();
         rdp.populate(conn);
      }catch(SQLException e){
         e.printStackTrace();
      }
   }

}

