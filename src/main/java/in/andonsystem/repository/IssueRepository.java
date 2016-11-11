/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Issue;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Md Zahid Raza
 */
public interface IssueRepository {
   public void persist(Issue issue);
   public Issue getByKey(Long id);
   public List<Issue> findAll();
   public List<Issue> findAll(Date after);
}
