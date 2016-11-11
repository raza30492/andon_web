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
public class SectionRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired
    SectionRepository sectionRepository;
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Section]: Repository");
        Assert.assertEquals(sectionRepository.findAll().size(), 6);
    }
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[Section]: Repository");
        Assert.assertNotNull(sectionRepository.getByKey(1));
        Assert.assertNull(sectionRepository.getByKey(7));
    }

    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:sections.sql", false);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("SECTION");
    }
}
