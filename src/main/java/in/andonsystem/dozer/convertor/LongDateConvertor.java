/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dozer.convertor;

import java.util.Date;
import org.dozer.DozerConverter;

/**
 *
 * @author Md Zahid Raza
 */
public class LongDateConvertor extends DozerConverter<Long, Date>{

    public LongDateConvertor() {
        super(Long.class, Date.class);
    }

    @Override
    public Date convertTo(Long source, Date destination) {
        System.out.println("convertTo: LongDateConvertor");
        return new Date(source);
    }

    @Override
    public Long convertFrom(Date source, Long destination) {
        System.out.println("convertFrom: LongDateConvertor");
        return source.getTime();
    }
   
}
