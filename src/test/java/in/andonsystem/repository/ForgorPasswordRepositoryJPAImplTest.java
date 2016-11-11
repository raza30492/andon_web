/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.ForgotPassword;
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
public class ForgorPasswordRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired 
    ForgotPasswordRepository forgotPasswordRepository;
    
    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:forgot_passwords.sql", false);
    }
    
    //@Test
    public void persist(){
        System.out.println("persist[ForgotPassword]: Repository");
        ForgotPassword forgotPassword = new ForgotPassword(33333, 777777, new Date());
        forgotPasswordRepository.persist(forgotPassword);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        Assert.assertEquals(countRowsInTable("FORGOT_PASSWORD"), 3);
    }
    
    //@Test
    public void getByKey(){
        System.out.println("getByKey[ForgotPassword]: Repository");
        Assert.assertNotNull(forgotPasswordRepository.getByKey(11111));
        Assert.assertNull(forgotPasswordRepository.getByKey(33333));
        
    }
    
    //@Test
    public void delete(){
        System.out.println("delete[ForgotPassword]: Repository");
        forgotPasswordRepository.delete(22222);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        Assert.assertEquals(countRowsInTable("FORGOT_PASSWORD"), 1);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("FORGOT_PASSWORD");
    }
}
