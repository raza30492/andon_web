/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.dto.LoginForm;
import in.andonsystem.dto.UserPreferences;
import in.andonsystem.entity.User;
import in.andonsystem.service.UserService;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Md Zahid Raza
 */
@Controller
public class MiscController {
   private static final Logger LOG = Logger.getLogger("MiscController");
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private UserPreferences userPrefs;
   
    @RequestMapping(value = {"/","/home"})
    public String index(){
        LOG.info("/home");
        return "index";
    }
   
    @RequestMapping(value = {"/login/show"},method = RequestMethod.GET)
    public String showLogin(ModelMap model){
        LOG.info("/login/show");
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
        return "login";
    }
   
   @RequestMapping(value = {"/login/auth"},method = RequestMethod.POST)
   public String authLogin(@Valid LoginForm loginForm,BindingResult result,ModelMap model){
      LOG.info("login/auth");
      if(result.hasErrors()){
         return "login";
      }
      User user = userService.authenticate(loginForm);
      if(user != null){
         userPrefs.setUsername(user.getName());
         userPrefs.setLevel(user.getDesignation().getLevel());
         return "index";
      }else{
         model.addAttribute("message", "Incorrect Combination of Employee Id, Password and Role. Try Again!");
         return "login";
      }
   }
   
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        LOG.info("/logout");
        request.getSession().invalidate();
        return "index";
    }
   
}
