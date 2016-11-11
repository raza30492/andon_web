/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.dozer.Mapping;

/**
 *
 * @author Md Zahid Raza
 */
public class UserForm {
   
   @Mapping("id")
   @Pattern(regexp = "[0-9]{5}")
   private String empId;
   
   @Size(min = 3,max = 50)
   private String name;
   
   private String email;
   private String passwd;
 
   @Pattern(regexp = "[0-9]{10}")
   private String mobile;
   
   @NotNull
   private Integer desgnId;

   public UserForm() {
   }

   public UserForm(String empId, String name, String mobile,Integer desgnId) {
      this.empId = empId;
      this.name = name;
      this.mobile = mobile;
      this.desgnId = desgnId;
   }

   public String getEmpId() {
      return empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPasswd() {
      return passwd;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public Integer getDesgnId() {
      return desgnId;
   }

   public void setDesgnId(Integer desgnId) {
      this.desgnId = desgnId;
   }
   
   
}
