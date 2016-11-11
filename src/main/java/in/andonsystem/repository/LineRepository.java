/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.repository;

import in.andonsystem.entity.Line;

/**
 *
 * @author Md Zahid Raza
 */
public interface LineRepository {
    
    public Line getByKey(Integer key);
}
