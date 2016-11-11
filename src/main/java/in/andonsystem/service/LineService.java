/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Designation;
import in.andonsystem.entity.Line;
import in.andonsystem.repository.LineRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class LineService {
    
    @Autowired
    LineRepository lineRepository;
    
    public void addDesignation(Integer lineNo,Integer desgnId){
        Line line = lineRepository.getByKey(lineNo);
        line.getDesignations().add(new Designation(desgnId));
    }
    
    public void addDesignations(Integer lineNo,List<Integer> desgnIdList){
        Line line = lineRepository.getByKey(lineNo);
        Set<Designation> designations = line.getDesignations();
        Iterator<Integer> itr = desgnIdList.iterator();
        
        while(itr.hasNext()){
            designations.add(new Designation(itr.next()));
        }
    }
}
