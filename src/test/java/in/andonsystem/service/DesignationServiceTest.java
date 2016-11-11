/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Designation;
import in.andonsystem.repository.DesignationRepository;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class DesignationServiceTest {
    
    @InjectMocks DesignationService desgnService;
    
    @Mock DesignationRepository desgnRepository;
    
    @Mock List<Designation> list;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Designation]: Service");
        when(desgnRepository.findAll()).thenReturn(list);
        Assert.assertEquals(desgnService.findAll(), list);
        verify(desgnRepository,atLeastOnce()).findAll();
    }
}
