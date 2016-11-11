/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.dto.LoginForm;
import in.andonsystem.dto.UserForm;
import in.andonsystem.entity.Designation;
import in.andonsystem.entity.ForgotPassword;
import in.andonsystem.entity.User;
import in.andonsystem.repository.DesignationRepository;
import in.andonsystem.repository.ForgotPasswordRepository;
import in.andonsystem.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Tuple;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class UserServiceTest {
    
    @InjectMocks UserService userService;
    
    @Mock UserRepository userRepository;
    
    @Mock DesignationRepository designationRepository;
    
    @Mock ForgotPasswordRepository forgotPasswordRepository;
    
    @Mock Mapper mapper;
    
    @Spy List<User> users = new ArrayList<>();
    
    @Mock List<Tuple> tuples;
    
    @Spy UserForm userForm;
    
    @Spy LoginForm loginForm;
    
    @Spy ForgotPassword forgotPassword;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loginForm = new LoginForm("55555", "test", 4);
        userForm = new UserForm("55555", "Test", "1234567890",1);
        forgotPassword = new ForgotPassword(11111, 222222, new Date());
        
        users.add(new User());
        users.add(new User(55555, "ABC", "password", "8888888888"));
    }
    
    //@Test
    public void authenticate(){
        System.out.println("authenticate: Service");
        User user = users.get(0);
        when(userRepository.authenticate(any(Integer.class), any(String.class), any(Integer.class))).thenReturn(user);       
        Assert.assertEquals(userService.authenticate(loginForm), user);
        verify(userRepository,atLeastOnce()).authenticate(any(Integer.class), any(String.class), any(Integer.class));
    }
    
    //@Test
    public void saveUser(){
        System.out.println("saveUser: Service");
        User user = users.get(0);
        when(mapper.map(userForm, User.class)).thenReturn(user);
        when(designationRepository.getByKey(any(Integer.class))).thenReturn(mock(Designation.class));
        doNothing().when(userRepository).persist(user);
        
        userService.saveUser(userForm);
        Assert.assertNotNull(user.getPasswd());
        Assert.assertNotNull(user.getDesignation());
        
        verify(mapper,atLeastOnce()).map(userForm, User.class);
        verify(designationRepository,atLeastOnce()).getByKey(any(Integer.class));
        verify(userRepository,atLeastOnce()).persist(user);
    }
    
    //@Test
    public void findAll(){
        System.out.println("findAll[User]: Service");
        when(userRepository.findAll()).thenReturn(users);
        Assert.assertEquals(userService.findAll(), users);
        verify(userRepository,atLeastOnce()).findAll();
    }
    
    //@Test
    public void finadAllByDesgnId(){
        System.out.println("finadAllByDesgnId[User]: Service");
        when(userRepository.findAllByDesgnId(any(Integer.class))).thenReturn(tuples);
        Assert.assertEquals(userService.findAllByDesgnId(any(Integer.class)), tuples);
        verify(userRepository,atLeastOnce()).findAllByDesgnId(any(Integer.class));
    }
    
    //@Test
    public void findByEmpId(){
        System.out.println("findByEmpId[User]: Service");
        User user = users.get(0);
        when(userRepository.getByKey(any(Integer.class))).thenReturn(user);
        Assert.assertEquals(userService.findByEmpId(any(Integer.class)), user);
        verify(userRepository,atLeastOnce()).getByKey(any(Integer.class));
    }
    
    //@Test
    public void update(){
        System.out.println("update: Service");
        User user = users.get(1);
        when(userRepository.getByKey(any(Integer.class))).thenReturn(user);
        when(designationRepository.getByKey(any(Integer.class))).thenReturn(mock(Designation.class));
        
        userService.update(userForm);
        
        Assert.assertEquals(user.getName(), userForm.getName());
        Assert.assertEquals(user.getEmail(), userForm.getEmail());
        Assert.assertEquals(user.getMobile(), userForm.getMobile());
        Assert.assertNotNull(user.getDesignation());
        
        verify(userRepository,atLeastOnce()).getByKey(any(Integer.class));
        verify(designationRepository,atLeastOnce()).getByKey(any(Integer.class));
    }
    
    //@Test
    public void delete(){
        System.out.println("delete: Service");
        doNothing().when(userRepository).delete(any(Integer.class));
        userService.delete(any(Integer.class));
        verify(userRepository,atLeastOnce()).delete(any(Integer.class));
    }
    
    //@Test
    public void sendOTPNewWithSuccess(){    //New: create new ForgotPassword and persist
        System.out.println("sendOTPNewWithSuccess: Service");
        User user = users.get(1);
        
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(null);
        doNothing().when(forgotPasswordRepository).persist(any(ForgotPassword.class));
        
        Assert.assertTrue(userService.sendOTP(anyInt()));
        
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
        verify(forgotPasswordRepository,atLeastOnce()).persist(any(ForgotPassword.class));
    }
    
    //@Test
    public void sendOTPExistingWithSuccess(){ ////Exizting: Override existing ForgotPassword and update
        System.out.println("sendOTPExistingWithSuccess: Service");
        User user = users.get(1);
        
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(forgotPassword);
        
        Assert.assertTrue(userService.sendOTP(anyInt()));
        
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void sendOTPWithFailure(){
        System.out.println("sendOTPWithFailure: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.sendOTP(anyInt()));
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void resendOTPWithSuccess(){
        System.out.println("resendOTPWithSuccess: Service");
        User user = users.get(1);
        
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(forgotPassword);
        
        Assert.assertTrue(userService.resendOTP(anyInt()));
        
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void resendOTPWithFailureUserNotExist(){
        System.out.println("resendOTPWithFailureUserNotExist: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.resendOTP(anyInt()));
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void resendOTPWithFailureOTPNotExist(){
        System.out.println("resendOTPWithFailureOTPNotExist: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(mock(User.class));
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(null);
        
        Assert.assertFalse(userService.resendOTP(anyInt()));
        
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void verifyOTPWithSuccess(){
        System.out.println("verifyOTPWithSuccess: Service");
        forgotPassword = new ForgotPassword(11111, 222222, new Date());
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(forgotPassword);
        Assert.assertTrue(userService.verifyOTP(anyInt(), 222222));
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void verifyOTPWithFailureOTPNotMatch(){
        System.out.println("verifyOTPWithFailureOTPNotMatch: Service");
        forgotPassword = new ForgotPassword(11111, 222222, new Date());
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(forgotPassword);
        Assert.assertFalse(userService.verifyOTP(anyInt(), 333333));
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void verifyOTPWithFailureOTPNotSent(){
        System.out.println("verifyOTPWithFailureOTPNotSent: Service");
        when(forgotPasswordRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.verifyOTP(anyInt(), 111111));
        verify(forgotPasswordRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changePasswordWithSuccess(){
        System.out.println("changePasswordWithSuccess: Service");
        User user = users.get(0);
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        Assert.assertTrue(userService.changePassword(0, "password"));
        Assert.assertNotNull(user.getPasswd());
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changePasswordWithFailure(){
        System.out.println("changePasswordWithFailure: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.changePassword(0, anyString()));
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changeEmailWithSuccess(){
        System.out.println("changeEmailWithSuccess: Service");
        User user = users.get(0);
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        Assert.assertTrue(userService.changeEmail(0, anyString()));
        Assert.assertNotNull(user.getEmail());
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changeEmailWithFailure(){
        System.out.println("changeEmailWithFailure: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.changeEmail(0, anyString()));
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changeMobileWithSuccess(){
        System.out.println("changeMobileWithSuccess: Service");
        User user = users.get(0);
        when(userRepository.getByKey(anyInt())).thenReturn(user);
        Assert.assertTrue(userService.changeMobile(0, anyString()));
        Assert.assertNotNull(user.getMobile());
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changeMobileWithFailure(){
        System.out.println("changeMobileWithFailure: Service");
        when(userRepository.getByKey(anyInt())).thenReturn(null);
        Assert.assertFalse(userService.changeMobile(0, anyString()));
        verify(userRepository,atLeastOnce()).getByKey(anyInt());
    }
    
    //@Test
    public void changePasswordProfileWithSuccess(){
        System.out.println("changePasswordProfileWithSuccess: Service");
        User user = users.get(0);
        when(userRepository.authenticate(anyInt(),anyString())).thenReturn(user);
        Assert.assertTrue(userService.changePassword(0, "currPassword","newPassword"));
        Assert.assertNotNull(user.getPasswd());
        verify(userRepository,atLeastOnce()).authenticate(anyInt(),anyString());
    }
    
    //@Test
    public void changePasswordProfileWithFailure(){
        System.out.println("changePasswordProfileWithFailure: Service");
        when(userRepository.authenticate(anyInt(),anyString())).thenReturn(null);
        Assert.assertFalse(userService.changePassword(0, "currPassword","newPassword"));
        verify(userRepository,atLeastOnce()).authenticate(anyInt(),anyString());
    }
    
}
