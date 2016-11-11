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
public class LineRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired
    LineRepository lineRepository;
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[Line]: Repository");
        Assert.assertNotNull(lineRepository.getByKey(1));
        Assert.assertNull(lineRepository.getByKey(9));
    }

    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:lines.sql", false);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("LINE");
    }
}
