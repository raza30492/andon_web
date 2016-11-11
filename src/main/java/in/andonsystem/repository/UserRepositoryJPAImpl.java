/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;


import in.andonsystem.entity.Designation_;
import in.andonsystem.entity.User;
import in.andonsystem.entity.User_;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("userDao")
public class UserRepositoryJPAImpl extends AbstractJPARepository<Integer, User> implements UserRepository{

   private Logger logger = Logger.getLogger("UserDaoJPAImpl");
   
   @Override
   public void persist(User entity) {
      super.persist(entity); //To change body of generated methods, choose Tools | Templates.
   }
   
   @Override
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
   public User authenticate(Integer empId,String passwd,int level) {      
      CriteriaBuilder builder = em.getCriteriaBuilder();
      
      CriteriaQuery<User> query = builder.createQuery(User.class);
      Root<User> usr = query.from(User.class);
      
      ParameterExpression<Integer> empIdParam = builder.parameter(Integer.class);
      ParameterExpression<String> passwdParam = builder.parameter(String.class);
      ParameterExpression<Integer> levelParam = builder.parameter(Integer.class);
      
      query.select(usr).where(builder.and(
              builder.equal(usr.get(User_.id), empIdParam),
              builder.equal(usr.get(User_.password), passwdParam),
              builder.equal(usr.get(User_.designation).get(Designation_.level), levelParam)
      ));
      
      try{
         return em.createQuery(query)
              .setParameter(empIdParam, empId)
              .setParameter(passwdParam, passwd)
              .setParameter(levelParam, level)
              .getSingleResult();
      }catch(NoResultException e){
         logger.info("No User found.[Authentication unsuccessfull]");
         return null;
      }
   }
   
   @Override
    public User authenticate(Integer empId, String passwd) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<User> query = builder.createQuery(User.class);
      Root<User> usr = query.from(User.class);
      
      ParameterExpression<Integer> empIdParam = builder.parameter(Integer.class);
      ParameterExpression<String> passwdParam = builder.parameter(String.class);
      
      query.select(usr).where(builder.and(
              builder.equal(usr.get(User_.id), empIdParam),
              builder.equal(usr.get(User_.password), passwdParam)
      ));
      
      try{
         return em.createQuery(query)
              .setParameter(empIdParam, empId)
              .setParameter(passwdParam, passwd)
              .getSingleResult();
      }catch(NoResultException e){
         logger.info("No User found.[Authentication unsuccessfull]");
         return null;
      }
    }

   @Override
   public void delete(Integer key) {
      super.delete(key); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public User getByKey(Integer key) {
      return super.getByKey(key);
   }

   @Override
   public List<User> findAll() {
      return super.findAll();
   }

   /**
    * Find all users corresponding to particular designation. 
    * @param desgnId designation Id
    * @return List of Tuple containing (empId,name) 
    */
   @Override
   public List<Tuple> findAllByDesgnId(int desgnId) {
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
      Root<User> user = query.from(User.class);
      
      Path<Integer> empId = user.get(User_.id);
      Path<String> name = user.get(User_.name);
      
      ParameterExpression<Integer> desgnIdParam = builder.parameter(Integer.class);
      
      query.multiselect(empId,name);
      query.where(builder.equal(user.get(User_.designation).get(Designation_.id), desgnIdParam));
      
      return em.createQuery(query).setParameter(desgnIdParam, desgnId).getResultList();
      
   }

    
   
   
   
   
}
