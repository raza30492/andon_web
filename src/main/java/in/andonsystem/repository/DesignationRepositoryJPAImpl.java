/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Designation;
import in.andonsystem.entity.Designation_;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("designationDao")
public class DesignationRepositoryJPAImpl extends AbstractJPARepository<Integer, Designation> implements DesignationRepository{


   @Override
   public void persist(Designation entity) {
      super.persist(entity); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Designation getByKey(Integer key) {
      return super.getByKey(key); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public List<Designation> findAll() {
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Designation> query = builder.createQuery(Designation.class);
      Root<Designation> desgn = query.from(Designation.class);
      query.orderBy(builder.asc(desgn.get(Designation_.name)));
      return em.createQuery(query).getResultList();
   }
   
}
