/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dozer.convertor;

import in.andonsystem.entity.Line;
import org.dozer.DozerConverter;

/**
 *
 * @author Administrator
 */
public class IntegerLineConvertor extends DozerConverter<Integer, Line>{
    
    public IntegerLineConvertor() {
        super(Integer.class,Line.class);
    }

    @Override
    public Line convertTo(Integer source, Line destination) {
        if(source == null) return null;
        return new Line(source);
    }

    @Override
    public Integer convertFrom(Line source, Integer destination) {
        if(source == null) return null;
        return source.getLineNo();
    }
    
}
