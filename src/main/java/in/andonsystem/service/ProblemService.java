/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Designation;
import in.andonsystem.entity.Problem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.andonsystem.repository.ProblemRepository;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Md Zahid Raza
 */
@Service
@Transactional
public class ProblemService {
   
   @Autowired
   private ProblemRepository problemRepository;
   
   public List<Problem> findAll(){
      return problemRepository.findAll();
   }
   
   public void addDesignation(Integer probId,Integer desgnId){
       Problem problem = problemRepository.getByKey(probId);
       problem.getDesignations().add(new Designation(desgnId));
   }
   
   public void addDesignations(Integer probId,List<Integer> desgnIdList){
       Problem problem = problemRepository.getByKey(probId);
       Set<Designation> designations = problem.getDesignations();
       Iterator<Integer> itr = desgnIdList.iterator();
       while(itr.hasNext()){
           designations.add(new Designation(itr.next()));
       }
   }
   
}
