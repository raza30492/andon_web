/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.dto.IssueDto;
import in.andonsystem.entity.Issue;
import in.andonsystem.repository.IssueRepository;
import in.andonsystem.util.DozerUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Md Zahid Raza
 */
@Service
@Transactional
public class IssueService {
   
    private static final Logger LOG = Logger.getLogger("IssueService");

    @Autowired
    private Mapper mapper;

    @Autowired
    private IssueRepository issueRepository;

    /**
     * Raise Issue.
     * @param issueDto IssueDto Object
     * @return issueId of Raised Issue
     */
    public Long raiseIssue(IssueDto issueDto){
      //LOG.info("raiseIssue(): Service");
      Issue issue = mapper.map(issueDto, Issue.class);

      Date timeNow = new Date();
      issue.setRaisedAt(timeNow);
      issue.setModAt(timeNow);
      issue.setProcessingAt((short)1);  //Initially Processing at Level 1
      issue.setStatus('O'); // Initial status Open[O]     
      issueRepository.persist(issue);
      return issue.getId();
   }
   
    /**
     * Get Issue by issueId
     * @param id issueId
     * @return IssueDto Object
     */
    public IssueDto getIssue(Long id){
        Issue issue = issueRepository.getByKey(id);
        if(issue != null){
            return mapper.map(issue, IssueDto.class);
        }else{
           return null;
        }
    }
    
    public IssueDto updateIssue(Long id,IssueDto issueDto){
        if(!id.equals(issueDto.getId())){
            return null;
        }
        Issue issue = issueRepository.getByKey(id);
        if(issue != null){
            issue = mapper.map(issueDto, Issue.class);
            return mapper.map(issue, IssueDto.class);
        }else{
           return null;
        }
    }
    
    /**
     * find all Issues.
     * @return List of IssueDto Object
     */
    public List<IssueDto> findAll(){
        List<Issue> issues = issueRepository.findAll();
        List<IssueDto> issueDtos = DozerUtil.map(mapper, issues, IssueDto.class);
        return issueDtos;
    }
    
    /**
     * find All Issue after given time
     * @param after time in Milliseconds
     * @return  List of IssueDto Object
     */
    public List<IssueDto> findAll(Long after){
        List<Issue> issues = issueRepository.findAll(new Date(after));
        List<IssueDto> issueDtos = DozerUtil.map(mapper, issues, IssueDto.class);
        return issueDtos;
    }
    
    /**
     * Acknowledge Issue.
     * @param issueId issueId
     * @param ackBy acknowledged by User
     * @return true on Success. false when Issue Not found.
     */
    public Boolean acknowledge(Long issueId,Integer ackBy){
        Issue issue = issueRepository.getByKey(issueId);
        if(issue != null){
            Date timeNow = new Date();
            issue.setAckBy(ackBy);
            issue.setAckAt(timeNow);
            issue.setModAt(timeNow);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Save Seek Help Event.
     * @param issueId issueId
     * @param helpSoughtByLevel  Help Sought by Level
     * @return true on Success. false when Issue Not found.
     */
    public Boolean seekHelp(Long issueId,Short helpSoughtByLevel){
        Issue issue = issueRepository.getByKey(issueId);
        if(issue != null){
            Date timeNow = new Date();
            issue.setSeekHelp(helpSoughtByLevel);
            issue.setProcessingAt(++helpSoughtByLevel);
            issue.setModAt(timeNow);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Fix Issue.
     * @param issueId issueId
     * @param fixBy Fixed by User
     * @return true on Success. false when Issue Not found.
     */
    public Boolean fix(Long issueId,Integer fixBy){
        Issue issue = issueRepository.getByKey(issueId);
        if(issue != null){
            Date timeNow = new Date();
            issue.setFixBy(fixBy);
            issue.setFixAt(timeNow);
            issue.setModAt(timeNow);
            issue.setProcessingAt((short)4);
            issue.setStatus('C');
            return true;
        }else{
            return false;
        }
    }
}
