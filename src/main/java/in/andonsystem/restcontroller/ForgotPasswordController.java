/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.restcontroller;

import in.andonsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = {"/api/forgot_passwd"})
public class ForgotPasswordController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = {"/send_otp"},method = RequestMethod.POST)
    public ResponseEntity<String> sendOTP(@RequestParam("empId") Integer empId){
        Boolean result = userService.sendOTP(empId);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail, User not found.",HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = {"/resend_otp"},method = RequestMethod.GET)
    public ResponseEntity<String> resendOTP(@RequestParam("empId") Integer empId){
        Boolean result = userService.resendOTP(empId);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail, User not found or Send not commited first.",HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = {"/verify_otp"},method = RequestMethod.GET)
    public ResponseEntity<String> verifyOTP(@RequestParam("empId") Integer empId,@RequestParam("otp") Integer otp){
        Boolean result = userService.verifyOTP(empId,otp);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail",HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = {"/change_password"},method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestParam("empId") Integer empId,@RequestParam("password") String password){
        Boolean result = userService.changePassword(empId,password);
        if(result){
            return ResponseEntity.ok("success");
        }else{
            return new ResponseEntity<>("fail, User not found",HttpStatus.NOT_FOUND);
        }
    }
}
