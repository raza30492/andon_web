/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Designation;
import in.andonsystem.entity.User;
import in.andonsystem.util.PasswordUtil;
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
public class UserRepositoryJPAImplTest extends AbstractRepositoryTestSetup{
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    DesignationRepository desgnRepository;

    
    @BeforeTransaction
    protected void beforeTransaction() {
        executeSqlScript("classpath:designations.sql", false);
        executeSqlScript("classpath:users.sql", false);
    }
    
    //@Test
    public void authenticateWithLevel(){
        System.out.println("authenticateWithLevel[User]: Repository");
        Assert.assertNotNull(userRepository.authenticate(55555, PasswordUtil.encrypt("8987525008"), 1));
        Assert.assertNull(userRepository.authenticate(55550, "8987525008", 2));
    }
    //@Test
    public void authenticateWithoutLevel(){
        System.out.println("authenticateWithoutLevel[User]: Repository");
        Assert.assertNotNull(userRepository.authenticate(55555, PasswordUtil.encrypt("8987525008")));
        Assert.assertNull(userRepository.authenticate(55550, "8987525008"));
    }
   
    //@Test
    public void persist(){
        System.out.println("persist[User]: Repository");
        Designation designation = desgnRepository.getByKey(1);
        Assert.assertNotNull(designation);
        User user = new User(55551, "Test User", "", PasswordUtil.encrypt("password"), "8987525008",designation );
        userRepository.persist(user);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        Assert.assertEquals(countRowsInTable("USER"), 3);
    }

    //@Test
    public void findAll(){
        System.out.println("findAll[User]: Repository");
        Assert.assertEquals(userRepository.findAll().size(), 2);
    }

    //@Test
    public void findAllByDesgnId(){
        System.out.println("findAllByDesgnId[User]: Repository");
        Assert.assertEquals(userRepository.findAllByDesgnId(1).size(), 1);
    }

    //@Test
    public void getByKey(){
        System.out.println("getByKey[User]: Repository");
        Assert.assertNotNull(userRepository.getByKey(55555));
        Assert.assertNull(userRepository.getByKey(12345));
    }

    //@Test
    public void delete(){
        System.out.println("delete[User]: Repository");
        userRepository.delete(55550);
        TestTransaction.flagForCommit();
        TestTransaction.end();
        Assert.assertEquals(countRowsInTable("USER"), 1);
    }

    @AfterTransaction
    protected void afterTransaction(){
        deleteFromTables("USER","DESIGNATION");
    }
}
