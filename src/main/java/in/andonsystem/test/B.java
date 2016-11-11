/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.test;

import java.util.Date;

/**
 *
 * @author Md Zahid Raza
 */
public class B {
   private Date date;

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   @Override
   public String toString() {
      return "B{" + "date=" + date + '}';
   }

   
}
