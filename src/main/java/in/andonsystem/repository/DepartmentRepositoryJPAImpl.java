/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Department;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("departmentRepository")
public class DepartmentRepositoryJPAImpl extends AbstractJPARepository<Integer, Department> implements DepartmentRepository{

   @Override
   public List<Department> findAll() {
      return super.findAll(); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void persist(Department entity) {
      throw new UnsupportedOperationException("Not supported yet.");//To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Department getByKey(Integer key) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
}
