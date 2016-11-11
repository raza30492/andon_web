/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Issue;
import in.andonsystem.entity.Issue_;
import java.util.Date;
import java.util.List;
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
@Repository("issueRepository")
public class IssueRepositoryJPAImpl extends AbstractJPARepository<Long, Issue> implements IssueRepository{

    @Override
    public void delete(Long key) {
        super.delete(key); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Issue entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Issue getByKey(Long key) {
        return super.getByKey(key); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Issue> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Issue> findAll(Date after) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Issue> query = builder.createQuery(Issue.class);
        Root<Issue> issue = query.from(Issue.class);
        
        ParameterExpression<Date> dateParam = builder.parameter(Date.class);
        query.select(issue).where(builder.greaterThan(issue.get(Issue_.modAt), dateParam));
        
        return em.createQuery(query)
                .setParameter(dateParam, after)
                .getResultList();
    }
    
}
