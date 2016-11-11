/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.test;

import in.andonsystem.dto.IssueDto;
import in.andonsystem.entity.Issue;
import in.andonsystem.entity.Line;
import in.andonsystem.entity.Problem;
import in.andonsystem.entity.Section;
import in.andonsystem.util.MiscUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Md Zahid Raza
 */
public class Main {
   public static void main(String[] args) {
       
       List<String> list = new ArrayList<>();
       list.add("dozer_mapping.xml");
       Mapper mapper = new DozerBeanMapper();
       
       Issue issue = new Issue(new Line(1), new Section(1), new Problem(1), 'Y', 0, "problem Test", 55555);
       
       IssueDto issueDto = mapper.map(issue, IssueDto.class);
       System.out.println(issueDto.getSecId());
       System.out.println(issueDto.getLineNo());
   }
}
