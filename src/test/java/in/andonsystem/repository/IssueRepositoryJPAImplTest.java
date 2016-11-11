/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Issue;
import in.andonsystem.entity.Line;
import in.andonsystem.entity.Problem;
import in.andonsystem.entity.Section;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TestTransaction;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Administrator
 */
@Test(enabled = false)
public class IssueRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired
    IssueRepository issueRepository;
    
    @Autowired
    LineRepository lineRepository;
    
    @Autowired
    SectionRepository sectionRepository;
    
    @Autowired
    ProblemRepository problemRepository;
    
    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:lines.sql", false);
        executeSqlScript("classpath:sections.sql", false);
        executeSqlScript("classpath:departments.sql", false);
        executeSqlScript("classpath:problems.sql", false);
        executeSqlScript("classpath:issues.sql", false);
    }
    
    //@Test
    public void persist(){
        System.out.println("persist[Issue]: Repository");
        Line line = lineRepository.getByKey(1);
        Assert.assertNotNull(line);
        Section section = sectionRepository.getByKey(1);
        Assert.assertNotNull(section);
        Problem problem = problemRepository.getByKey(1);
        Assert.assertNotNull(problem);
        
        Issue issue = new Issue(line, section, problem, 'Y', 0, "problem xyz was detected", 55555);
        
        Date timeNow = new Date();
        issue.setRaisedAt(timeNow);
        issue.setModAt(timeNow);
        
        issueRepository.persist(issue);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        Assert.assertEquals(countRowsInTable("ISSUE"), 3);
    }
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[Issue]: Repository");
        Assert.assertNotNull(issueRepository.getByKey(1L));
        Assert.assertNull(issueRepository.getByKey(3L));
    }
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Issue]: Repository");
        Assert.assertEquals(issueRepository.findAll().size(), 2);
    }
    
    //@Test
    public void findAllAfter(){
        System.out.println("findAllAfter[Issue]: Repository");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date after = null;
        try{
            after = sdf.parse("2016-08-12 11:00:00");
        }catch(ParseException e){System.out.println(e);}
        
        Assert.assertEquals(issueRepository.findAll(after).size(), 1);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("ISSUE","LINE","SECTION","PROBLEM","DEPARTMENT");
    }
    
}
