/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Md Zahid Raza
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
   
    private static final Logger LOG = Logger.getLogger("DepartmentController");
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showDepartment(){
        LOG.info("/department/");
        return "department";
    }
}
