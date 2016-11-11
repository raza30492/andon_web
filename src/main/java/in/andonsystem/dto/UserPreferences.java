/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Md Zahid Raza
 */
@Component("userPref")
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPreferences {
   
   private String username;
   private int level;
   
   public UserPreferences(){}

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public int getLevel() {
      return level;
   }

   public void setLevel(int level) {
      this.level = level;
   }
   
}
