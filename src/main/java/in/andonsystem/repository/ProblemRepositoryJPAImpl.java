/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Problem;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("problemDao")
public class ProblemRepositoryJPAImpl extends AbstractJPARepository<Integer, Problem> implements ProblemRepository{


   @Override
   public void persist(Problem entity) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Problem getByKey(Integer key) {
      return super.getByKey(key);
   }

   @Override
   public List<Problem> findAll() {
      return super.findAll(); //To change body of generated methods, choose Tools | Templates.
   }
   
}
