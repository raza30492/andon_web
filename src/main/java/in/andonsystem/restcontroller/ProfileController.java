/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.restcontroller;

import in.andonsystem.service.UserService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = {"/api/profile"})
public class ProfileController {
    
    private static final Logger LOG = Logger.getLogger("ProfileController");
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = {"/change_email"},method = RequestMethod.PUT)
    public ResponseEntity<String> changeEmail(@RequestParam("empId") Integer empId,@RequestParam("email") String email){
        LOG.info("Change Email: Employee Id = " + empId + ", Email = " + email);
        
        Boolean result = userService.changeEmail(empId, email);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return ResponseEntity.ok("fail, User not Found.");
        }
        
    }
    
    @RequestMapping(value = {"/change_mobile"},method = RequestMethod.PUT)
    public ResponseEntity<String> changeMobile(@RequestParam("empId") Integer empId,@RequestParam("mobile") String mobile){
        LOG.info("Change Mobile: Employee Id = " + empId + ", Mobile No. = " + mobile);
        
        Boolean result = userService.changeMobile(empId, mobile);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return ResponseEntity.ok("fail, User not Found.");
        }
    }
    
    @RequestMapping(value = {"/change_password"},method = RequestMethod.PUT)
    public ResponseEntity<String> changePassword(
            @RequestParam("empId") Integer empId,
            @RequestParam("currPassword") String currPassword,
            @RequestParam("newPassword") String newPassword
    ){
        LOG.info("Change Password: Employee Id = " + empId );
        
        Boolean result = userService.changePassword(empId, currPassword,newPassword);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return ResponseEntity.ok("fail, Authentication Failed.");
        }
    }
}
