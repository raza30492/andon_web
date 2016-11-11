/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.config.PersistenceJPATestConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 *
 * @author Md Zahid Raza
 */
@ContextConfiguration(classes = {PersistenceJPATestConfig.class})
public abstract class AbstractRepositoryTestSetup extends AbstractTransactionalTestNGSpringContextTests{

}
