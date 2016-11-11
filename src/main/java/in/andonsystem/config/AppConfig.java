package in.andonsystem.config;

import in.andonsystem.test.MiscUtil;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"in.andonsystem"})
@Import(DispatcherConfig.class)
public class AppConfig extends WebMvcConfigurerAdapter{

   @Bean
   public Mapper dozerBeanMapper(){
       List<String> list = new ArrayList<>();
       list.add("dozer_mapping.xml");
      return new DozerBeanMapper(list);
   }
  
}
