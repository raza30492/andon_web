/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Department;
import in.andonsystem.entity.Problem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.andonsystem.repository.DepartmentRepository;
import org.hibernate.Hibernate;

/**
 *
 * @author Md Zahid Raza
 */
@Service
@Transactional
public class DepartmentService{

   @Autowired
   private DepartmentRepository departmentRepository;
   
   public List<Department> findAll(Boolean InitProblem) {
        List<Department> departments = departmentRepository.findAll();
        if(InitProblem){  //Initialize both Problem and Designations of Problem
            for(Department department : departments){
                Hibernate.initialize(department.getProblems());
                for(Problem problem : department.getProblems()){
                    Hibernate.initialize(problem.getDesignations());
                }
            }
        }
        return departments;
   }
   
}
