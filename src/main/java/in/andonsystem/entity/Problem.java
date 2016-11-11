/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Md Zahid Raza
 */
@Entity
@Table(name = "PROBLEM")
public class Problem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROB_ID",nullable = false,unique = true)
    private int id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "DESGN_PROBLEM",
            joinColumns = @JoinColumn(name = "PROB_ID"),
            inverseJoinColumns = @JoinColumn(name = "DESGN_ID")           
    )
    private Set<Designation> designations = new HashSet<>();

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    private Department department;

    public Problem(){}
    //For Instantiation while testing
    public Problem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Designation> getDesignations() {
        return designations;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Problem other = (Problem) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Problem{" + "id=" + id + ", name=" + name + ", department=" + department + '}';
    }
    
    
    
    
}
