/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.dto.IssueDto;
import in.andonsystem.entity.Issue;
import in.andonsystem.entity.Line;
import in.andonsystem.entity.Problem;
import in.andonsystem.entity.Section;
import in.andonsystem.repository.IssueRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class IssueServiceTest {
    
    @InjectMocks IssueService issueService;
    
    @Mock IssueRepository issueRepository;
    
    @Mock Mapper mapper;
    
    @Spy List<Issue> issues = new ArrayList<>();
    
    @Spy List<IssueDto> issueDtos = new ArrayList<>();
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        issues.add(new Issue());
        Issue issue2 = new Issue(new Line(1), new Section(1), new Problem(1), 'Y', 0, "problem2", 55555);
        issues.add(issue2);
        
        issueDtos.add(new IssueDto(1, 1, 1, 'N', 0, "Test Problem", 55555));
        issueDtos.add(new IssueDto());
        
    }
    
    //@Test
    public void raiseIssue(){
        System.out.println("raiseIssue: Service");
        IssueDto issueDto = issueDtos.get(0);
        Issue issue = issues.get(0);
        
        when(mapper.map(issueDto, Issue.class)).thenReturn(issue);
        doNothing().when(issueRepository).persist(issue);
        
        issueService.raiseIssue(issueDto);

        Assert.assertNotNull(issue.getRaisedAt());
        Assert.assertNotNull(issue.getModAt());
        Assert.assertEquals(issue.getProcessingAt(),new Short("1"));
        Assert.assertEquals(issue.getStatus(), 'O');
        
        verify(mapper,atLeastOnce()).map(issueDto, Issue.class);
        verify(issueRepository,atLeastOnce()).persist(issue);
    }
    
    //@Test
    public void getIssueFound(){
        System.out.println("getIssueFound: Service");
        Issue issue = issues.get(1);
        when(issueRepository.getByKey(any(Long.class))).thenReturn(issue);
        when(mapper.map(issue, IssueDto.class)).thenReturn(mock(IssueDto.class));
        
        Assert.assertNotNull(issueService.getIssue(any(Long.class)));
        
        verify(issueRepository,atLeastOnce()).getByKey(any(Long.class));
        verify(mapper,atLeastOnce()).map(issue, IssueDto.class);
        
    }
    
    //@Test
    public void getIssuesNotFound(){
        System.out.println("getIssuesNotFound: Service");
        when(issueRepository.getByKey(any(Long.class))).thenReturn(null);
        Assert.assertEquals(issueService.getIssue(any(Long.class)), null);
        verify(issueRepository,atLeastOnce()).getByKey(any(Long.class));
    }
    
    //@Test
    public void findAll(){
        System.out.println("findAll[Issue]: Service");
        when(issueRepository.findAll()).thenReturn(issues);
        Assert.assertNotNull(issueService.findAll());
        verify(issueRepository,atLeastOnce()).findAll();
    }
    
    //@Test
    public void findAllAfter(){
        System.out.println("findAllAfter[Issue]: Service");
        when(issueRepository.findAll(any(Date.class))).thenReturn(issues);
        Assert.assertNotNull(issueService.findAll(any(Long.class)));
        verify(issueRepository,atLeastOnce()).findAll(any(Date.class));
    }
    
    //@Test
    public void acknowledgeFound(){
        System.out.println("acknowledgeFound: Service");
        Issue issue = issues.get(0);
        when(issueRepository.getByKey(any(Long.class))).thenReturn(issue);
        
        Assert.assertTrue(issueService.acknowledge(anyLong(),0));
        Assert.assertNotNull(issue.getAckBy());
        Assert.assertNotNull(issue.getAckAt());
        Assert.assertNotNull(issue.getModAt());
        
        verify(issueRepository,atLeastOnce()).getByKey(any(Long.class));
    }
    
    //@Test
    public void acknowledgeNotFound(){
        System.out.println("acknowledgeNotFound: Service");
        when(issueRepository.getByKey(any(Long.class))).thenReturn(null);
        Assert.assertFalse(issueService.acknowledge(anyLong(),0));
    }
    
    //@Test
    public void seekHelpFound(){
        System.out.println("seekHelpFound: Service");
        Issue issue = issues.get(0);
        when(issueRepository.getByKey(any(Long.class))).thenReturn(issue);
        
        Assert.assertTrue(issueService.seekHelp(anyLong(),(short)0));
        Assert.assertNotNull(issue.getSeekHelp());
        Assert.assertNotNull(issue.getProcessingAt());
        Assert.assertNotNull(issue.getModAt());
        
        verify(issueRepository,atLeastOnce()).getByKey(any(Long.class));
    }
    
    //@Test
    public void seekHelpNotFound(){
        System.out.println("seekHelpNotFound: Service");
        when(issueRepository.getByKey(any(Long.class))).thenReturn(null);
        Assert.assertFalse(issueService.seekHelp(anyLong(),(short)0));
    }
    
    //@Test
    public void fixFound(){
        System.out.println("fixFound: Service");
        Issue issue = issues.get(0);
        when(issueRepository.getByKey(any(Long.class))).thenReturn(issue);
        
        Assert.assertTrue(issueService.fix(anyLong(),0));
        Assert.assertNotNull(issue.getFixBy());
        Assert.assertNotNull(issue.getFixAt());
        Assert.assertNotNull(issue.getModAt());
        Assert.assertNotNull(issue.getProcessingAt());
        Assert.assertNotNull(issue.getStatus());
        
        verify(issueRepository,atLeastOnce()).getByKey(any(Long.class));
    }
    
    //@Test
    public void fixNotFound(){
        System.out.println("fixNotFound: Service");
        when(issueRepository.getByKey(any(Long.class))).thenReturn(null);
        Assert.assertFalse(issueService.fix(anyLong(),0));
    }
}
