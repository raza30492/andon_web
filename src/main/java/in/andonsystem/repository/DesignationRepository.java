/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Designation;
import in.andonsystem.entity.User;
import java.util.List;

/**
 *
 * @author Md Zahid Raza
 */
public interface DesignationRepository {
   public Designation getByKey(Integer id);
   public List<Designation> findAll();
}
