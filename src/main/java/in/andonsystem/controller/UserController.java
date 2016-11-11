/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.dto.UserForm;
import in.andonsystem.entity.User;
import in.andonsystem.service.UserService;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Md Zahid Raza
 */
@Controller
@RequestMapping("/user")
public class UserController {
   
   private static final Logger LOG = Logger.getLogger("UserController");
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private Mapper mapper;
   
   /**
    * Controller for showing different pages.
    * @param page page to be shown. [view/update/remove]
    * @return Corresponding page
    */
    @RequestMapping(value = {"/show"},method = RequestMethod.GET)
    public String showUserPage(@RequestParam("page") String page){
        LOG.info("url: /user/show?page=" + page);
        if(page.equals("view")){
           return "user_view";
        }else if(page.equals("remove")){
           return "user_remove";
        }else if(page.equals("update")){
           return "user_update";
        }else{
           return "page_not_found";
        }
    }
   
   /**
    * Controller for finding all the users with particular designation. Request is asynchronous (Ajax Query)
    * @param page Request coming from page. [view/update/remove]
    * @param desgnId Designation Id 
    * @param req HttpServletRequest. Used for accessing contextPath
    * @return Response body for Ajax processing
    */
   @RequestMapping(value = {"/find"},method = RequestMethod.GET)
   @ResponseBody
   public String findUsersByDesgnId(
           @RequestParam("page") String page,
           @RequestParam("desgnId") int desgnId,
           HttpServletRequest req){
      
      LOG.info("url: /user/find?page="+page+"&desgnId=" + desgnId);
      
      String contextPath = req.getServletContext().getContextPath();
      
      List<Tuple> users = userService.findAllByDesgnId(desgnId);
      
      String respText = "";
      
      if(page.equals("view")){                
         respText = "<table class=\"table-bordered\" id=\"bordered-table\" width=\"100%\" >" +
                                 "<tr>\n" +
                                 "    <th width=\"35%\" class=\"align-center\">Employee Id</th>\n" +
                                 "    <th class=\"align-center\">User Name</th>\n" +
                                 "</tr>";

         for(Tuple user : users){
             respText += "<tr>\n" +
                         "       <td>" + user.get(0) +"</td>"+
                         "       <td><a href=\""+contextPath+"/user/view?empId="+user.get(0)+"\" >"+ user.get(1) +"</a></td>"+
                         "</tr>";
         }

         respText += "</table>";
      }
      if(page.contains("update")){                
          respText = "<table class=\"table-bordered\" id=\"bordered-table\" width=\"100%\" >" +
                                  "<tr>\n" +
                                  "    <th width=\"35%\" class=\"align-center\">Employee Id</th>\n" +
                                  "    <th class=\"align-center\">User Name</th>\n" +
                                  "    <th class=\"align-center\">Action</th>\n" +
                                  "</tr>";

          for(Tuple user : users){
              respText += "<tr>\n" +
                          "       <td>" + user.get(0) +"</td>"+
                          "       <td>" + user.get(1) +"</td>"+
                          "       <td><a class=\"btn btn-success\" href=\""+contextPath+"/user/update?empId="+user.get(0)+"\" >Update</a></td>"+
                          "</tr>";
          }

          respText += "</table>";
      }
      if(page.contains("remove")){                
          respText = "<table class=\"table-bordered\" id=\"bordered-table\" width=\"100%\" >" +
                                  "<tr>\n" +
                                  "    <th width=\"35%\" class=\"align-center\">Employee Id</th>\n" +
                                  "    <th class=\"align-center\">User Name</th>\n" +
                                  "    <th class=\"align-center\">Action</th>\n" +
                                  "</tr>";

          for(Tuple user : users){
              respText += "<tr>\n" +
                          "       <td>" + user.get(0) +"</td>"+
                          "       <td>" + user.get(1) +"</td>"+
                          "       <td><a class=\"btn btn-danger\" href=\""+contextPath+"/user/remove?empId="+user.get(0)+"\" >Remove</a></td>"+
                          "</tr>";
          }

          respText += "</table>";
      }

      return respText;
   }
   
   /**
    * Show the details of particular user.
    * @param empId Employee Id of user.
    * @param model model for embedding User Object
    * @return view page with user model.
    */
   @RequestMapping(value = {"/view"},method = RequestMethod.GET)
   public String viewUser(@RequestParam("empId") int empId,ModelMap model){
      LOG.info("url: /user/view?empId=" + empId);
      User user = userService.findByEmpId(empId);
      model.addAttribute("user",user);
      model.addAttribute("action", "user_details");
      return "user_view";
   }
   
   /**
    * Update the details of particular user.
    * @param empId Employee Id of user.
    * @param model model
    * @return update page with user model.
    */
   @RequestMapping(value = {"/update"},method = RequestMethod.GET)
   public String updateUserShow(@RequestParam("empId") int empId,ModelMap model){
      LOG.info("url: /user/update?empId=" + empId);
      User user = userService.findByEmpId(empId);
      UserForm userForm = mapper.map(user, UserForm.class);
      userForm.setDesgnId(user.getDesignation().getId());
      model.addAttribute("userForm",userForm);
      model.addAttribute("action", "user_details");
      return "user_update";
   }
   
   @RequestMapping(value = {"/update"},method = RequestMethod.POST)
   public String updateUser(@Valid UserForm userForm,BindingResult result,ModelMap model){
      LOG.info("url: /user/update");
      if(result.hasErrors()){
         return "user_update";
      }
      userService.update(userForm);
      model.addAttribute("message", "User details updated successfully!");
      return "user_update";
   }
   
   
   /**
    * Remove particular user.
    * @param empId Employee Id of user.
    * @return remove page with message.
    */
   @RequestMapping(value = {"/remove"})
   public String removeUser(@RequestParam("empId") int empId,ModelMap model){
      LOG.info("url: /user/remove?empId=" + empId);
      userService.delete(empId);
      model.addAttribute("message", "User removed successfully!");
      return "user_remove";
   }
   
   /**
    * Show user registration form page.
    * @param model model in which user object is attached.
    * @return user registration page.
    */
   @RequestMapping(value = {"/register/form"},method = RequestMethod.GET)
   public String registerForm(ModelMap model){
      LOG.info("url: /user/register/form");
      UserForm userForm = new UserForm();
      model.addAttribute("userForm",userForm);
      return "user_register";
   }
   
   /**
    * Register user with the given details.
    * @param userForm User Form inputs.
    * @param result Binding result object for checking errors.
    * @param model model object
    * @return User registration page with message/errors.
    */
   @RequestMapping(value = {"/register"},method = RequestMethod.POST)
   public String register(@Valid UserForm userForm,BindingResult result,ModelMap model){
      LOG.info("url: /user/register");
      if(result.hasErrors()){
         return "user_register";
      }
      userService.saveUser(userForm);
      model.addAttribute("message", "User Registered successfully!");
      model.addAttribute("userForm",new UserForm());
      return "user_register";
   }

}
