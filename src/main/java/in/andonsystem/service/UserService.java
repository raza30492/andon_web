/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.dto.LoginForm;
import in.andonsystem.dto.UserForm;
import in.andonsystem.entity.ForgotPassword;
import in.andonsystem.entity.User;
import java.util.List;
import javax.persistence.Tuple;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.andonsystem.repository.DesignationRepository;
import in.andonsystem.repository.ForgotPasswordRepository;
import in.andonsystem.repository.UserRepository;
import in.andonsystem.util.MiscUtil;
import in.andonsystem.util.PasswordUtil;
import in.andonsystem.util.SMSUtil;
import java.util.Date;

/**
 *
 * @author Md Zahid Raza
 */
@Service
@Transactional
public class UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private Mapper mapper;


    /**
     * Business Service method(BSM) for authenticating Web Application User
     * @param loginForm login credentials collected from view
     * @return User object if authentication successful.
     */
    public User authenticate(LoginForm loginForm){
       return userRepository.authenticate(Integer.parseInt(loginForm.getEmpId()), PasswordUtil.encrypt(loginForm.getPasswd()),loginForm.getLevel());
    }

    /**
     * Business Service method(BSM) for saving User. user input collected from view is converted to 
     * User Object using Dozer API as Mapper
     * @param userForm user values collected from view page
     */
    public void saveUser(UserForm userForm){
       User user = mapper.map(userForm, User.class);
       user.setPasswd(PasswordUtil.encrypt(userForm.getMobile()));
       user.setDesignation(designationRepository.getByKey(userForm.getDesgnId()));
       userRepository.persist(user);
    }

    /**
     * BSM for finding all the users
     * @return List of all User Objects
     */
    public List<User> findAll(){
       return userRepository.findAll();
    }

    /**
     * BSM for finding all users corresponding to a designation.
     * @param desgnId Designation Id
     * @return List of Tuple containing (empId,name)
     */
    public List<Tuple> findAllByDesgnId(int desgnId){
       return userRepository.findAllByDesgnId(desgnId);
    }

    /**
     * BSM for finding a particular user with given employee id.
     * @param empId Employee Id.
     * @return User object if found. Null if not found.
     */
    public User findByEmpId(Integer empId){
       return userRepository.getByKey(empId);
    }

    /**
     * Update User details from Web Application. [Admin access]
     * @param userForm user details collected in form.
     */
    public void update(UserForm userForm){
        User user = userRepository.getByKey(Integer.parseInt(userForm.getEmpId()));
        if(user != null){
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setMobile(userForm.getMobile());
            user.setDesignation(designationRepository.getByKey(userForm.getDesgnId()));
        }

    }

    /**
     * BSM for deleting particular user.
     * @param empId  employee Id to delete.
     */
    public void delete(int empId){
       userRepository.delete(empId);
    }

    /**
     * Send 6 digit OTP.
     * @param empId Employee Id of User.
     * @return true: If User Exist. Either New ForgotPassword is saved or existing is updated.<br> 
     * false: If User does not Exist
     */
    public Boolean sendOTP(Integer empId){
        User user = userRepository.getByKey(empId);
        if(user != null){
            Integer otp = Integer.parseInt(MiscUtil.getOTP());

            Date timeNow = new Date();
            ForgotPassword forgotPassword = forgotPasswordRepository.getByKey(empId);
            if(forgotPassword != null){
                forgotPassword.setOtp(otp);
                forgotPassword.setTime(timeNow);
            }else{
                forgotPassword = new ForgotPassword(empId, otp, timeNow);
                forgotPasswordRepository.persist(forgotPassword);
            }
           
            String mobile = user.getMobile();
            String message = "OTP to reset password in ANDON SYSTEM APPLICATION is: " + otp;
            SMSUtil.sendSMS(mobile, message);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * ReSend 6 digit OTP.
     * @param empId Employee Id of User
     * @return true: If User Exist and OTP is sent before.<br>
     * false: If User does not Exist or OTP not sent before.
     */
    public Boolean resendOTP(Integer empId){
        User user = userRepository.getByKey(empId);
        if(user != null){
            ForgotPassword forgotPassword = forgotPasswordRepository.getByKey(empId);
            if(forgotPassword != null){
                Integer otp = forgotPassword.getOtp();
                String mobile = user.getMobile();
                String message = "OTP to reset password in ANDON SYSTEM APPLICATION is: " + otp;
                SMSUtil.sendSMS(mobile, message);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    /**
     * Verify OTP.
     * @param empId Employee Id of User.
     * @param otp 6 digit OTP to be verified.
     * @return  true: If OTP is sent before and given OTP matches.<br>
     *          false:If OTP is not sent before or OTP does not macth.
     */
    public Boolean verifyOTP(Integer empId,Integer otp){
        ForgotPassword forgotPassword = forgotPasswordRepository.getByKey(empId);
        if(forgotPassword != null){
            return forgotPassword.getOtp().equals(otp);
        }else{
            return false;
        }
    }
   
    /**
     * Change Password of user. Accessed by Client using Forgot Password feature.
     * @param empId Employee Id of User.
     * @param password New Password.
     * @return true: If User Exists. false: If User does not Exist.
     */
    public Boolean changePassword(Integer empId,String password){
        User user = userRepository.getByKey(empId);
        if(user != null){
            user.setPasswd(PasswordUtil.encrypt(password));
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Change Email.
     * @param empId Employee Id of User.
     * @param email New Email Id of User.
     * @return true: If User Exists. false: If User does not Exist.
     */
    public Boolean changeEmail(Integer empId,String email){
        User user = userRepository.getByKey(empId);
        if(user != null){
            user.setEmail(email);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Change Mobile.
     * @param empId Employee Id of User.
     * @param mobile  New Mobile Number of User.
     * @return true: If User Exists. false: If User does not Exist.
     */
    public Boolean changeMobile(Integer empId,String mobile){
        User user = userRepository.getByKey(empId);
        if(user != null){
            user.setMobile(mobile);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Change Password of user. Accessed by Client using change Password feature in Profile.
     * @param empId Employee Id of User.
     * @param currPassword current Password
     * @param newPassword  New Password.
     * @return  true: If authentication with current password successful.<br> 
     *          false: If authentication with current password not successful.
     */
    public Boolean changePassword(Integer empId,String currPassword,String newPassword){
        User user = userRepository.authenticate(empId, PasswordUtil.encrypt(currPassword));
        if(user != null){
            user.setPasswd(PasswordUtil.encrypt(newPassword));
            return true;
        }else{
            return false;
        }
    }
    
}
