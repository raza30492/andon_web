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
public class DepartmentRepositoryJPAImplTest extends AbstractRepositoryTestSetup{

    @Autowired 
    DepartmentRepository departmentRepository;

    public void findAll(){
        System.out.println("findAll[Department]: Repository");
        Assert.assertEquals(departmentRepository.findAll().size(), 2);
    }

    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:departments.sql", false);
    }
    
    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("DEPARTMENT");
    }

}
