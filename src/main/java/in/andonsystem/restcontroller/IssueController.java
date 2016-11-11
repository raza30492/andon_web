/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.restcontroller;

import in.andonsystem.dto.IssueDto;
import in.andonsystem.service.IssueService;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Md Zahid Raza
 */
@RestController
@RequestMapping("/api/issue")
public class IssueController {
   
    private static final Logger LOG = Logger.getLogger("IssueRestController");
   
    @Autowired
    private IssueService issueService;
    
    /**
     * Get Issue with given issueId
     * @param id issueId
     * @return Issue JsonObject
     */
    @RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
    public ResponseEntity<IssueDto>  getIssue(@PathVariable("id") Long id){
        LOG.info("Get Issue with id: " + id);
        IssueDto issueDto = issueService.getIssue(id);
        if(issueDto != null){
            return ResponseEntity.ok(issueDto);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT)
    public ResponseEntity<IssueDto>  updateIssue(@PathVariable("id") Long id,@RequestBody IssueDto issue){
        LOG.info("Update Issue with id: " + id);
        IssueDto issueDto = issueService.updateIssue(id,issue);
        if(issueDto != null){
            return ResponseEntity.ok(issueDto);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Get All the Issues.
     * @return Array of Issue JsonObjects
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>>  getAllIssues(){
        LOG.info("Get All Issues...");
        List<IssueDto> issues = issueService.findAll();
        if(issues.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(issues,HttpStatus.OK);
        }
    }
    
    /**
     * Get All the Issues After given time.
     * @param after Time in Milliseconds
     * @return Array of Issue JsonObjects
     */
    @RequestMapping(value = {"/after"},method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>>  getAllIssuesAfter(@RequestParam("date") Long after){
        LOG.info("Get All Issues After " + after);
        List<IssueDto> issues = issueService.findAll(after);
        if(issues.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(issues,HttpStatus.OK);
        }
    }

    /**
     * Raise Issue.
     * @param issue Issue Object
     * @param ucBuilder UriComponentBuilder for building Location URI
     * @return success message with Created(201) Header
     */
    @RequestMapping(method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseEntity<String> raiseIssue(@RequestBody IssueDto issue,UriComponentsBuilder ucBuilder){
      LOG.info("Raising issue...");
      long id = issueService.raiseIssue(issue);
      URI location = ucBuilder.path("/api/issue/{id}").buildAndExpand(id).toUri();       
      return ResponseEntity.created(location).body("success");
    }

    /**
     * Acknowledge Issue.
     * @param issueId issueId of Issue
     * @param ackBy acknowledged By User
     * @return success(HTTP 200)/ fail(HTTP 404)
     */
    @RequestMapping(value = {"/acknowledge"},method = RequestMethod.PUT)
    public ResponseEntity<String> acknowledge(@RequestParam("issueId") Long issueId,@RequestParam("ackBy") Integer ackBy){
        LOG.info("acknowledge: issueId = " + issueId + ",ackBy = " + ackBy);
        Boolean result = issueService.acknowledge(issueId, ackBy);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail",HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Save Seek Help Event
     * @param issueId issueId of Issue
     * @param level Help Sought by Level
     * @return success(HTTP 200)/ fail(HTTP 404)
     */
    @RequestMapping(value = {"/seek_help"},method = RequestMethod.PUT)
    public ResponseEntity<String> seekHelp(@RequestParam("issueId") Long issueId,@RequestParam("level") Short level){
        LOG.info("Seek Help: issueId = " + issueId + ", Level = " + level);
        Boolean result = issueService.seekHelp(issueId, level);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail",HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Fix the Issue.
     * @param issueId issueId of Issue
     * @param fixBy Fixed by User
     * @return success(HTTP 200)/ fail(HTTP 404)
     */
    @RequestMapping(value = {"/fix"},method = RequestMethod.PUT)
    public ResponseEntity<String> fixIssue(@RequestParam("issueId") Long issueId,@RequestParam("fixBy") Integer fixBy){
        LOG.info("acknowledge: issueId = " + issueId + ",fixBy = " + fixBy);
        Boolean result = issueService.fix(issueId, fixBy);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail",HttpStatus.NOT_FOUND);
        }
    }
    
}
/*
{
    "lineNo":1,
    "secId":1,
    "probId":1,
    "critical":"Y",
    "operatorNo":"657788",
    "description":"Probelm xyz was detected",
    "raisedBy":"55550"
}
*/
