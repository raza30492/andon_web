/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.apitest;

import in.andonsystem.dto.IssueDto;
import in.andonsystem.entity.Issue;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Administrator
 */
public class IssueAPITest {
    
    public static final String REST_SERVICE_URI = "http://localhost:8084/andonsystem/api";
    
    /*GET*/
    public static void getIssue(){
        System.out.println("\n--------------Testing getIssue API-----------------");
        RestTemplate restTemplate = new RestTemplate();
        IssueDto issue = restTemplate.getForObject(REST_SERVICE_URI+"/issue/1", IssueDto.class);
        System.out.println(issue);
    }
    /*GET*/
    public static void getAllIssues(){
        System.out.println("\n--------------Testing getAllIssues API-----------------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String,Object>> issuesMap =  restTemplate.getForObject(REST_SERVICE_URI+"/issue", List.class);
        for(int i = 0; i < 3; i++){
            System.out.println("issueDto"+issuesMap.get(i).toString());
        }
        if(issuesMap.size() > 3){
            System.out.println(""+(issuesMap.size()-3) + " Issues skipped...");
        }
        
    }
    /*GET*/
    public static void getAllIssuesAfter(){
        System.out.println("\n--------------Testing getAllIssuesAfter API-----------------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String,Object>> issuesMap =  restTemplate.getForObject(REST_SERVICE_URI+"/issue/after?date=1471397257000", List.class);
        for(int i = 0; i < issuesMap.size(); i++){
            if(i == 3) break;
            System.out.println("issueDto"+issuesMap.get(i).toString());
        }
        if(issuesMap.size() > 3){
            System.out.println(""+(issuesMap.size()-3) + " Issues skipped...");
        }
        
    }
    /*POST*/
    public static void raiseIssue(){
        System.out.println("\n-------------Testing raiseIssue API --------------");
        RestTemplate restTemplate = new RestTemplate();
        IssueDto issue = new IssueDto(1, 1, 1, 'Y', 0, "Test Problem", 55555);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/issue", issue,IssueDto.class);
        System.out.println("Location: " + uri.toASCIIString());
    }
    /*PUT*/
    public static void acknowledge(){
        System.out.println("\n-------------Testing acknowledge API --------------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_SERVICE_URI+"/issue/acknowledge?issueId=1&ackBy=55555", null);
    }
    /*PUT*/
    public static void seekHelp(){
        System.out.println("\n-------------Testing seekHelp API --------------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_SERVICE_URI+"/issue/seek_help?issueId=1&level=1", null);
    }
    /*PUT*/
    public static void fixIssue(){
        System.out.println("\n-------------Testing fixIssue API --------------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_SERVICE_URI+"/issue/fix?issueId=1&fixBy=55555", null);
    }
    
    
    public static void main(String[] args) {
        //raiseIssue();
        getIssue();
        acknowledge();
        seekHelp();
        fixIssue();
        getAllIssues();
        getAllIssuesAfter();
        
    }
}
