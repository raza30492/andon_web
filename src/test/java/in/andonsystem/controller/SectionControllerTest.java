/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class SectionControllerTest {
    @InjectMocks SectionController sectionController;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    //@Test
    public void showSection(){
        System.out.println("showSection: Controller");
        Assert.assertEquals(sectionController.showSection(), "section");
    }
}
