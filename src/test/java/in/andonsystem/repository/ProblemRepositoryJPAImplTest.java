/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Administrator
 */
@Test(enabled = false)
public class ProblemRepositoryJPAImplTest extends AbstractRepositoryTestSetup{

    @Autowired
    ProblemRepository problemRepository;
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Problem]: Repository");
        Assert.assertEquals(problemRepository.findAll().size(), 4);
    }
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[Problem]: Repository");
        Assert.assertNotNull(problemRepository.getByKey(1));
        Assert.assertNull(problemRepository.getByKey(5));
    }
    
    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:departments.sql", false);
        executeSqlScript("classpath:problems.sql", false);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("PROBLEM","DEPARTMENT");
    }
    
}
