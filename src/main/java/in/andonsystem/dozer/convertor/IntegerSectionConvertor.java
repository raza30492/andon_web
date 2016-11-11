/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dozer.convertor;

import in.andonsystem.entity.Section;
import org.dozer.DozerConverter;

/**
 *
 * @author Administrator
 */
public class IntegerSectionConvertor extends DozerConverter<Integer, Section>{

    public IntegerSectionConvertor() {
        super(Integer.class, Section.class);
    }

    @Override
    public Section convertTo(Integer source, Section destination) {
        if(source == null) return null;
        return new Section(source);
    }

    @Override
    public Integer convertFrom(Section source, Integer destination) {
        if(source == null) return null;
        return source.getId();
    }

   
}
