package in.andonsystem.repository;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractJPARepository<PK extends Serializable, T> {
  
   @PersistenceContext
   protected EntityManager em;

   private final Class<T> persistentClass;
   
   @SuppressWarnings("unchecked")
   public AbstractJPARepository(){
      this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
   }

   /**
    * Dao method for getting all entities
    * @return List of All entity Objects
    */
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
   public List<T> findAll(){
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<T> query = builder.createQuery(persistentClass);
      query.from(persistentClass);
      return em.createQuery(query).getResultList();
   }
   
   /**
    * Dao method for finding Entity by primary key
    * @param key Primary Key of Entity
    * @return Entity Object Corresponding to key. Null if not found.
    */
   @SuppressWarnings("unchecked")
   public T getByKey(PK key) {
      return (T) em.find(persistentClass, key);
   }

   /**
    * Dao method for Saving Entity Object in database
    * @param entity Entity Object to be persisted
    */
   public void persist(T entity) {
      em.persist(entity);
   }
   
   /**
    * Dao method for deleting Entity Object from database 
    * @param key Key of entity to be removed
    */
   public void delete(PK key) {
      T entity = getByKey(key);
      if( entity != null){
         em.remove(entity);
      }
   }

}
