/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.dto.LoginForm;
import in.andonsystem.dto.UserPreferences;
import in.andonsystem.entity.Designation;
import in.andonsystem.entity.User;
import in.andonsystem.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Md Zahid Raza
 */
@Test(enabled = false)
public class MiscControllerTest {
    
    @InjectMocks MiscController miscController;
    
    @Mock UserService userService;
    
    @Mock MessageSource messageSource;
    
    @Mock BindingResult result;
    
    @Mock UserPreferences userPref;
    
    @Mock HttpServletRequest request;
    
    @Mock HttpSession session;
    
    @Mock LoginForm loginForm;
    
    @Mock User user;
    
    @Spy ModelMap model;
  
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loginForm = new LoginForm("55555", "password", 4);
        user = new User(55555, "Test", "test@gmail.com", "password", "9999988888", new Designation(1,"Admin",4));
    }
    
    //@Test
    public void index(){
        System.out.println("index: Controller");
        Assert.assertEquals(miscController.index(), "index");
    }

    //@Test
    public void showlogin(){
        System.out.println("showlogin: Controller");
        Assert.assertEquals(miscController.showLogin(model), "login");
        Assert.assertNotNull(model.get("loginForm"));
    }
    
    //@Test
    public void authLoginWithValidationError(){
        System.out.println("authLoginWithValidationError: Controller");
        when(result.hasErrors()).thenReturn(true);
        Assert.assertEquals(miscController.authLogin(loginForm, result, model), "login");
    }
    
    //@Test
    public void authLoginWithAuthenticationFailure(){
        System.out.println("authLoginWithAuthenticationFailure: Controller");
        when(result.hasErrors()).thenReturn(false);
        when(userService.authenticate(any(LoginForm.class))).thenReturn(null);
        Assert.assertEquals(miscController.authLogin(loginForm, result, model), "login");
        Assert.assertNotNull(model.get("message"));
        verify(userService,Mockito.atLeastOnce()).authenticate(any(LoginForm.class));
    }

    //@Test
    public void authLoginWithSuccess(){
        System.out.println("authLoginWithSuccess: Controller");
        when(result.hasErrors()).thenReturn(false);
        when(userService.authenticate(any(LoginForm.class))).thenReturn(user);
        doNothing().when(userPref).setUsername(any(String.class));
        doNothing().when(userPref).setLevel(any(Integer.class));
        
        Assert.assertEquals(miscController.authLogin(loginForm, result, model), "index");
        verify(result,atLeastOnce()).hasErrors();
        verify(userPref,atLeastOnce()).setUsername(any(String.class));
        verify(userPref,atLeastOnce()).setLevel(any(Integer.class));
        verify(userService,atLeastOnce()).authenticate(any(LoginForm.class));
    }
    
    //@Test
    public void logout(){
        System.out.println("logout: Controller");
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        Assert.assertEquals(miscController.logout(request), "index");
        verify(request,atLeastOnce()).getSession();
    }

}
