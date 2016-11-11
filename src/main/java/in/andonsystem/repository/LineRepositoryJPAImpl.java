/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Line;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Zahid Raza
 */
@Repository("lineRepository")
public class LineRepositoryJPAImpl extends AbstractJPARepository<Integer, Line> implements LineRepository{

    @Override
    public Line getByKey(Integer key) {
        return super.getByKey(key);
    }
    
}
