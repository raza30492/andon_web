/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.entity.Department;
import in.andonsystem.service.DepartmentService;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = true)
public class ProblemControllerTest {
    
    @InjectMocks ProblemController problemController;
    
    @Mock DepartmentService departmentService;
    
    @Mock List<Department> list;
    
    @Spy ModelMap model;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    public void showProblem(){
        System.out.println("showProblem: Controller");
        Assert.assertEquals(problemController.showProblem(), "problem");
    }
    
    public  void problemEscalation(){
        when(departmentService.findAll(Boolean.TRUE)).thenReturn(list);
        Assert.assertEquals(problemController.showProblemEscalation(model), "problem_escalation");
        Assert.assertEquals(model.get("departments"), list);
        verify(departmentService,atLeastOnce()).findAll(Boolean.TRUE);
    }
}
