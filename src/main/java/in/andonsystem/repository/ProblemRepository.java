/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Problem;
import java.util.List;

/**
 *
 * @author Md Zahid Raza
 */
public interface ProblemRepository {
   
   public List<Problem> findAll();
   
   public Problem getByKey(Integer key);
}
