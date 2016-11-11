/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Md Zahid Raza
 */
public class LoginForm {
   
   @Pattern(regexp = "[0-9]{5}")
   private String empId;
   
   @NotEmpty
   private String passwd;
   
   @NotNull
   private Integer level;

   public LoginForm() {}

    public LoginForm(String empId, String passwd, Integer level) {
        this.empId = empId;
        this.passwd = passwd;
        this.level = level;
    }
   
   public String getEmpId() {
      return empId;
   }

   public void setEmpId(String empId) {
      this.empId = empId;
   }


   public String getPasswd() {
      return passwd;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public Integer getLevel() {
      return level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   @Override
   public String toString() {
      return "LoginForm{" + "empId=" + empId + ", passwd=" + passwd + ", level=" + level + '}';
   }
   
}
