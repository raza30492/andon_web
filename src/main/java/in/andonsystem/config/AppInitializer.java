package in.andonsystem.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
      ctx.register(AppConfig.class);
      ctx.setServletContext(servletContext);
      //Add Listeners
      servletContext.addListener(new ContextLoaderListener(ctx));
      servletContext.addListener(ContextListener.class);
      
      ServletRegistration.Dynamic servlet = servletContext.addServlet(
                  "dispatcher", new DispatcherServlet(ctx));

      servlet.setLoadOnStartup(1);
      servlet.addMapping("/");
   }
   
   
   /*
   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      // Create the 'root' Spring application context
      AnnotationConfigWebApplicationContext rootContext =
        new AnnotationConfigWebApplicationContext();
      rootContext.register(AppConfig.class);

      // Manage the lifecycle of the root application context
      servletContext.addListener(new ContextLoaderListener(rootContext));
      servletContext.addListener(ContextListener.class);
      
      // Create the dispatcher servlet's Spring application context
      AnnotationConfigWebApplicationContext dispatcherContext =
        new AnnotationConfigWebApplicationContext();
      dispatcherContext.register(DispatcherConfig.class);

      // Register and map the dispatcher servlet
      ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("/");
   }
*/
}
