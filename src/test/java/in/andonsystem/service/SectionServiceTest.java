/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Problem;
import in.andonsystem.entity.Section;
import in.andonsystem.repository.ProblemRepository;
import in.andonsystem.repository.SectionRepository;
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
public class SectionServiceTest {
    @InjectMocks SectionService sectionService;
    
    @Mock SectionRepository sectionRepository;
    
    @Mock List<Section> list;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Section]: Service");
        when(sectionRepository.findAll()).thenReturn(list);
        Assert.assertEquals(sectionService.findAll(), list);
        verify(sectionRepository,atLeastOnce()).findAll();
    }
}
