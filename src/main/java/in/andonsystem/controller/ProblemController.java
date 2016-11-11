/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.entity.Department;
import in.andonsystem.service.DepartmentService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Md Zahid Raza
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {
   
    private static final Logger LOG = Logger.getLogger("ProblemController");
    
    @Autowired
    DepartmentService departmentService;
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showProblem(){
       LOG.info("/problem/");
       return "problem";
    }

    @RequestMapping(value = "/escalation",method = RequestMethod.GET)
    public String showProblemEscalation(ModelMap model){
       LOG.info("/problem/escalation");
       List<Department> departments = departmentService.findAll(Boolean.TRUE);
       model.addAttribute("departments", departments);
       return "problem_escalation";
    }
}
