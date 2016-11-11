/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Problem;
import in.andonsystem.repository.ProblemRepository;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class ProblemServiceTest {
    
    @InjectMocks ProblemService problemService;
    
    @Mock ProblemRepository problemRepository;
    
    @Mock List<Problem> list;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    public void findAll(){
        System.out.println("findAll[Problem]: Service");
        when(problemRepository.findAll()).thenReturn(list);
        Assert.assertEquals(problemService.findAll(), list);
        verify(problemRepository,atLeastOnce()).findAll();
    }
}
