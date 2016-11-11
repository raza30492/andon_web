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
public class DesignationRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired
    DesignationRepository desgnRepository;
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Designation]: Repository");
        Assert.assertEquals(desgnRepository.findAll().size(), 2);
    }
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[Designation]: Repository");
        Assert.assertNotNull(desgnRepository.getByKey(1));
        Assert.assertNull(desgnRepository.getByKey(3));
    }

    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:designations.sql", false);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("DESIGNATION");
    }
}
