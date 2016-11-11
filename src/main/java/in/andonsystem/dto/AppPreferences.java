/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dto;

import in.andonsystem.entity.Department;
import in.andonsystem.entity.Designation;
import in.andonsystem.entity.Section;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/**
 *
 * @author Md Zahid Raza
 */
@Component(value = "appPref")
@Scope(value = "application",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AppPreferences {
  
   private List<Section> sections;
   
   private List<Department> departments;
   
   private List<Designation> desgns;
   
   public AppPreferences(){}

   public void initialize(List<Section> sections, List<Department> departments,List<Designation> desgns) {
      this.sections = sections;
      this.departments = departments;
      this.desgns = desgns;
   }
  
   public List<Section> getSections() {
      return sections;
   }

   public void setSections(List<Section> sections) {
      this.sections = sections;
   }

   public List<Department> getDepartments() {
      return departments;
   }

   public void setDepartments(List<Department> departments) {
      this.departments = departments;
   }

   public List<Designation> getDesgns() {
      return desgns;
   }

   public void setDesgns(List<Designation> desgns) {
      this.desgns = desgns;
   }
   

}
