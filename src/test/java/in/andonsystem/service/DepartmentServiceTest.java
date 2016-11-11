/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Department;
import in.andonsystem.repository.DepartmentRepository;
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
public class DepartmentServiceTest {
    
    @InjectMocks DepartmentService departmentService;
    
    @Mock DepartmentRepository departmentRepository;
    
    @Mock List<Department> list;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    public void findAllWithSectionsNonInitialized(){
        System.out.println("findAll[Department]: Service");
        when(departmentRepository.findAll()).thenReturn(list);
        Assert.assertEquals(departmentService.findAll(false), list);
        verify(departmentRepository,atLeastOnce()).findAll();
    }
    
}
