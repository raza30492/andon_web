/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.ForgotPassword;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository("forgotPasswordRepository")
public class ForgotPasswordRepositoryJPAImpl extends AbstractJPARepository<Integer, ForgotPassword> implements ForgotPasswordRepository{

    @Override
    public void delete(Integer key) {
        super.delete(key); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(ForgotPassword entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ForgotPassword getByKey(Integer key) {
        return super.getByKey(key); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ForgotPassword> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");//To change body of generated methods, choose Tools | Templates.
    }
}
