/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.User;
import java.util.List;
import javax.persistence.Tuple;

/**
 *
 * @author Md Zahid Raza
 */
public interface UserRepository {
   /**
    * Dao method for authenticating user logging in Web Application 
    * @param empId Employee Id of User
    * @param passwd Password of User
    * @param level Level of User
    * @return User Object
    */
   public User authenticate(Integer empId,String passwd,int level);
   
   public User authenticate(Integer empId,String passwd);
   
   public void persist(User user);
   
   public List<User> findAll();
   
   public List<Tuple> findAllByDesgnId(int desgnId);
   
   public User getByKey(Integer empId);
   
   public void delete(Integer empId);
}
