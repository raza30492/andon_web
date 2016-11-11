/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.test;

import java.util.Date;
import org.dozer.DozerConverter;

/**
 *
 * @author Md Zahid Raza
 */
public class DozerConvertor1 extends DozerConverter<Long, Date>{

   public DozerConvertor1() {
      super(Long.class, Date.class);
   }

   @Override
   public Date convertTo(Long source, Date destination) {
      return new Date(source);
   }

   @Override
   public Long convertFrom(Date source, Long destination) {
      return source.getTime();
   }

}
