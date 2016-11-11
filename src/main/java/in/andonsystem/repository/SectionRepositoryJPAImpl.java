/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Section;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("secionDao")
public class SectionRepositoryJPAImpl extends AbstractJPARepository<Integer, Section> implements SectionRepository{

   @Override
   public List<Section> findAll() {
      return super.findAll(); //To change body of generated methods, choose Tools | Templates.
   }


   @Override
   public void persist(Section entity) {
      throw new UnsupportedOperationException("Not supported yet.");//To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Section getByKey(Integer key) {
      return super.getByKey(key);
   }
   
}
