/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.ForgotPassword;

/**
 *
 * @author Administrator
 */
public interface ForgotPasswordRepository {
    public ForgotPassword getByKey(Integer key);
    public void persist(ForgotPassword forgotPassword);
    public void delete(Integer key);
}
