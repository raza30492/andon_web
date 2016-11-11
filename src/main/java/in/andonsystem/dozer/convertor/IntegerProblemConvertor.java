/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dozer.convertor;

import in.andonsystem.entity.Problem;
import org.dozer.DozerConverter;

/**
 *
 * @author Administrator
 */
public class IntegerProblemConvertor extends DozerConverter<Integer, Problem>{

    public IntegerProblemConvertor() {
        super(Integer.class, Problem.class);
    }

    @Override
    public Problem convertTo(Integer source, Problem destination) {
        if(source == null) return null;
        return new Problem(source);
    }

    @Override
    public Integer convertFrom(Problem source, Integer destination) {
        if(source == null) return null;
        return source.getId();
    }
    
}
