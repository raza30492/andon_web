/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.controller;

import in.andonsystem.dto.UserForm;
import in.andonsystem.entity.Designation;
import in.andonsystem.entity.User;
import in.andonsystem.service.UserService;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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
public class UserControllerTest {
    
    @InjectMocks UserController userController;
    
    @Mock UserService userService;
    
    @Mock Mapper mapper;
    
    @Mock User user;
    
    @Mock UserForm userForm;
    
    @Mock BindingResult result;
    
    @Spy ModelMap model;
    
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    //@Test
    public void showUserPage(){
        System.out.println("showUserPage: Controller");
        Assert.assertEquals(userController.showUserPage("view"), "user_view");
        Assert.assertEquals(userController.showUserPage("update"), "user_update");
        Assert.assertEquals(userController.showUserPage("remove"), "user_remove");
        Assert.assertEquals(userController.showUserPage("other"), "page_not_found");
    }
    
    //@Test
    public void viewUser(){
        System.out.println("viewUser: Controller");
        when(userService.findByEmpId(any(Integer.class))).thenReturn(user);
        Assert.assertEquals(userController.viewUser(any(Integer.class), model), "user_view");
        Assert.assertEquals(model.get("user"), user);
        Assert.assertNotNull(model.get("action"));
        verify(userService,atLeastOnce()).findByEmpId(any(Integer.class));
    }
    
    //@Test
    public void updateUserShow(){
        System.out.println("updateUserShow: Controller");
        when(userService.findByEmpId(any(Integer.class))).thenReturn(user);
        when(mapper.map(user, UserForm.class)).thenReturn(userForm);
        when(user.getDesignation()).thenReturn(mock(Designation.class));
        doNothing().when(userForm).setDesgnId(any(Integer.class));
        
        Assert.assertEquals(userController.updateUserShow(any(Integer.class), model), "user_update");
        Assert.assertEquals(model.get("userForm"), userForm);
        Assert.assertNotNull(model.get("action"));
        
        verify(userService,atLeastOnce()).findByEmpId(any(Integer.class));
        verify(mapper,atLeastOnce()).map(user, UserForm.class);
        verify(userForm,atLeastOnce()).setDesgnId(any(Integer.class));
        verify(user,atLeastOnce()).getDesignation();
    }
    
    //@Test
    public void updateUserWithValidationErrors(){
        System.out.println("updateUserWithValidationErrors: Controller");
        when(result.hasErrors()).thenReturn(true);
        Assert.assertEquals(userController.updateUser(userForm, result, model), "user_update");
        verify(result,atLeastOnce()).hasErrors();
    }
    
    //@Test
    public void updateUserWithSuccess(){
        System.out.println("updateUserWithSuccess: Controller");
        when(result.hasErrors()).thenReturn(false);
        doNothing().when(userService).update(userForm);
        Assert.assertEquals(userController.updateUser(userForm, result, model), "user_update");
        Assert.assertNotNull(model.get("message"));
        verify(result,atLeastOnce()).hasErrors();
        verify(userService,atLeastOnce()).update(userForm);
    }
    
    //@Test
    public void removeUser(){
        System.out.println("removeUser: Controller");
        doNothing().when(userService).delete(any(Integer.class));
        Assert.assertEquals(userController.removeUser(any(Integer.class), model), "user_remove");
        Assert.assertNotNull(model.get("message"));
        verify(userService,atLeastOnce()).delete(any(Integer.class));
    }
    
    //@Test
    public void registerForm(){
        System.out.println("registerForm: Controller");
        Assert.assertEquals(userController.registerForm(model), "user_register");
        Assert.assertNotNull(model.get("userForm"));
    }
    
    //@Test
    public void registerWithValidationErrors(){
        System.out.println("registerWithValidationErrors: Controller");
        when(result.hasErrors()).thenReturn(true);
        Assert.assertEquals(userController.register(userForm, result, model), "user_register");
        verify(result,atLeastOnce()).hasErrors();
    }
    
    //@Test
    public void registerWithSuccess(){
        System.out.println("registerWithSuccess: Controller");
        when(result.hasErrors()).thenReturn(false);
        doNothing().when(userService).saveUser(userForm);
        Assert.assertEquals(userController.register(userForm, result, model), "user_register");
        Assert.assertNotNull(model.get("message"));
        Assert.assertNotNull(model.get("userForm"));
        verify(result,atLeastOnce()).hasErrors();
        verify(userService,atLeastOnce()).saveUser(userForm);
    }
    
}
